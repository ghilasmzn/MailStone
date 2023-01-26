package com.analyzer.analyzer.controller.mail;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.analyzer.analyzer.controller.IGlobal;
import com.analyzer.analyzer.controller.Http.HttpRequest;
import com.analyzer.analyzer.model.Client;
import com.analyzer.analyzer.model.Issue;
import com.analyzer.analyzer.model.Product;
import com.analyzer.analyzer.utils.FileSearch;
import com.analyzer.analyzer.utils.StringSimilarity;
import com.analyzer.analyzer.utils.XML.XMLReader;
import com.analyzer.analyzer.utils.XML.XMLWriter;
import com.analyzer.analyzer.utils.XML.message.save.Scodex000;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FetchObject implements IGlobal {

	private final String SEPARATOR_MAIL = "##";

	// private IssueRepository iRepo = BeanUtil.getBean(IssueRepository.class);

	/**
	 * Get the Client data
	 * 
	 * @param from
	 * @return
	 */
	public Client fetchClient(String from) {
		String[] infosClient = from.split(" ");
		Client client = new Client();
		client.setInfos();

		// Set the clients information
		for (int i = 0; i < infosClient.length; i++) {
			switch (i) {
				case 0:
					client.setFirstname(infosClient[i]);
					break;
				case 1:
					client.setLastname(infosClient[i]);
					break;
				case 2:
					String mail = infosClient[i];
					mail = mail.replace("<", "");
					mail = mail.replace(">", "");
					client.setEmail(mail);
					break;

				default:
					break;
			}
		}

		return client;
	}

	private String formatString(String text) {
		String whitespace_chars = "" /* dummy empty string for homogeneity */
				+ "\\u0009" // CHARACTER TABULATION
				+ "\\u000A" // LINE FEED (LF)
				+ "\\u000B" // LINE TABULATION
				+ "\\u000C" // FORM FEED (FF)
				+ "\\u000D" // CARRIAGE RETURN (CR)
				+ "\\u0020" // SPACE
				+ "\\u0085" // NEXT LINE (NEL)
				+ "\\u00A0" // NO-BREAK SPACE
				+ "\\u1680" // OGHAM SPACE MARK
				+ "\\u180E" // MONGOLIAN VOWEL SEPARATOR
				+ "\\u2000" // EN QUAD
				+ "\\u2001" // EM QUAD
				+ "\\u2002" // EN SPACE
				+ "\\u2003" // EM SPACE
				+ "\\u2004" // THREE-PER-EM SPACE
				+ "\\u2005" // FOUR-PER-EM SPACE
				+ "\\u2006" // SIX-PER-EM SPACE
				+ "\\u2007" // FIGURE SPACE
				+ "\\u2008" // PUNCTUATION SPACE
				+ "\\u2009" // THIN SPACE
				+ "\\u200A" // HAIR SPACE
				+ "\\u2028" // LINE SEPARATOR
				+ "\\u2029" // PARAGRAPH SEPARATOR
				+ "\\u202F" // NARROW NO-BREAK SPACE
				+ "\\u205F" // MEDIUM MATHEMATICAL SPACE
				+ "\\u3000" // IDEOGRAPHIC SPACE
		;
		/* A \s that actually works for Java’s native character set: Unicode */
		String whitespace_charclass = "[" + whitespace_chars + whitespace_chars + "]+";
		/* A \S that actually works for Java’s native character set: Unicode */
		String not_whitespace_charclass = "[^" + whitespace_chars + "]";

		return text.replace(whitespace_charclass, not_whitespace_charclass);

	}

	/**
	 * Get the product data
	 * 
	 * @param content
	 * @return
	 */
	public Product fetchProduct(String content) {
		content = content.replaceAll("[^a-zA-Z0-9-À-ÿ]", " ");
		content = content.replace("\n", " ");
		content = formatString(content);

		System.out.println("=========>" + content + "<============");

		ArrayList<String> contenu = new ArrayList<>();

		String[] tmp = content.split(" ");
		for (int i = 0; i < tmp.length; i++) {
			contenu.add(tmp[i]);
		}

		contenu.removeAll(Collections.singleton(null));
		contenu.removeAll(Collections.singleton(""));

		Product p = new Product();
		p.setInfos();

		for (int i = 0; i < contenu.size(); i++) {

			double score = StringSimilarity.similarity(contenu.get(i), "produit");
			if (score > 0.35) {
				p.setName(contenu.get(i + 1).toLowerCase());
			}

			score = StringSimilarity.similarity(contenu.get(i), "référence");
			if (score > 0.35) {
				p.setReference(contenu.get(i + 1).toLowerCase());
			}

			score = StringSimilarity.similarity(contenu.get(i), "marque");
			if (score > 0.35) {
				p.setBrand(contenu.get(i + 1).toLowerCase());
			}

			score = StringSimilarity.similarity(contenu.get(i), "garanti");
			if (score > 0.35) {
				p.setGatantee(true);
			}
		}
		return p;
	}

	/**
	 * Get the issue details
	 * 
	 * @param content
	 * @return
	 */
	public Issue fetchIssue(String content) {
		Issue i = new Issue();

		content = content.replaceAll("[^a-zA-Z0-9-À-ÿ]", " ");
		content = content.replace("\n", " ");
		content = formatString(content);

		i.setDescription(content);

		return i;
	}

	private void writeSaveCodex000(Issue i) {
		File folder = new File(FOLDER_ARCHIVED_SAVE);

		// Get the files inside the folder
		FileSearch fs = new FileSearch(folder, 1);
		fs.printFilesInDepth();

		// Get the number of files
		int numberFiles = fs.getFileInDepth().size();
		String filename = "save" + numberFiles + ".xml";
		String xmlFile = FOLDER_ARCHIVED_SAVE + SEPARATOR + filename;

		Scodex000.setData(i, FOLDER_DTD_SAVE + SEPARATOR + "save.dtd");
		new XMLWriter().writeXML(FOLDER_ARCHIVED_SAVE + SEPARATOR + filename, CODE_SAVE_OBJECT);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;

			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(xmlFile);
			String xmlSring = new XMLReader().convertXMLtoString(doc);

			new HttpRequest("/", xmlSring);

		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Get the content from the mail
	 * 
	 * @param from
	 * @param content
	 */
	public void analyzeContent(Date date, String from, String content) {
		String[] contenu = content.split(SEPARATOR_MAIL);

		if (contenu.length != 2) {
			System.err.println("Error ! The content is endomaged !");
			return;
		}

		Product p = new Product();
		p.setInfos();
		p = fetchProduct(contenu[0]);

		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
		p.setDate(formater.format(date).toString());

		Client c = new Client();
		c.setInfos();
		c = fetchClient(from);
		p.setClient(c);

		Issue i = fetchIssue(contenu[1]);
		i.setProduct(p);

		writeSaveCodex000(i);
	}
}