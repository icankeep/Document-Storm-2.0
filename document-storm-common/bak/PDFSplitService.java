/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdfwriter.COSWriter;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @Description: PDF分割类,将PDF分割为多个PDF,目前没有使用,存档待后续使用<p>
 * @author: passer<p>
 * @version：2019年5月18日 下午1:42:17<p>
 */
public final class PDFSplitService {
	private static final String PASSWORD = "-password";
	private static final String SPLIT = "-split";
	private static final String START_PAGE = "-startPage";
	private static final String END_PAGE = "-endPage";
	private static final String OUTPUT_PREFIX = "-outputPrefix";

	private PDFSplitService() {
	}

	/**
	 * Infamous main method.
	 *
	 * @param args Command line arguments, should be one and a reference to a file.
	 *
	 * @throws IOException If there is an error parsing the document.
	 */
	public static void main(String[] args) throws IOException {
		// suppress the Dock icon on OS X
		System.setProperty("apple.awt.UIElement", "true");

		PDFSplitService split = new PDFSplitService();
		split.split(args);
	}

	private void split(String[] args) throws IOException {
		String password = "";
		String split = null;
		String startPage = null;
		String endPage = null;
		Splitter splitter = new Splitter();
		String pdfFile = null;
		String outputPrefix = null;
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals(PASSWORD)) {
				i++;
				if (i >= args.length) {
					usage();
				}
				password = args[i];
			} else if (args[i].equals(SPLIT)) {
				i++;
				if (i >= args.length) {
					usage();
				}
				split = args[i];
			} else if (args[i].equals(START_PAGE)) {
				i++;
				if (i >= args.length) {
					usage();
				}
				startPage = args[i];
			} else if (args[i].equals(END_PAGE)) {
				i++;
				if (i >= args.length) {
					usage();
				}
				endPage = args[i];
			} else if (args[i].equals(OUTPUT_PREFIX)) {
				i++;
				outputPrefix = args[i];
			} else {
				if (pdfFile == null) {
					pdfFile = args[i];
				}
			}
		}

		if (pdfFile == null) {
			usage();
		} else {
			if (outputPrefix == null) {
				outputPrefix = pdfFile.substring(0, pdfFile.lastIndexOf('.'));
			}
			PDDocument document = null;
			List<PDDocument> documents = null;
			try {
				document = PDDocument.load(new File(pdfFile), password);

				int numberOfPages = document.getNumberOfPages();
				boolean startEndPageSet = false;
				if (startPage != null) {
					splitter.setStartPage(Integer.parseInt(startPage));
					startEndPageSet = true;
					if (split == null) {
						splitter.setSplitAtPage(numberOfPages);
					}
				}
				if (endPage != null) {
					splitter.setEndPage(Integer.parseInt(endPage));
					startEndPageSet = true;
					if (split == null) {
						splitter.setSplitAtPage(Integer.parseInt(endPage));
					}
				}
				if (split != null) {
					splitter.setSplitAtPage(Integer.parseInt(split));
				} else {
					if (!startEndPageSet) {
						splitter.setSplitAtPage(1);
					}
				}

				documents = splitter.split(document);
				for (int i = 0; i < documents.size(); i++) {
					PDDocument doc = documents.get(i);
					String fileName = outputPrefix + "-" + (i + 1) + ".pdf";
					writeDocument(doc, fileName);
					doc.close();
				}

			} finally {
				if (document != null) {
					document.close();
				}
				for (int i = 0; documents != null && i < documents.size(); i++) {
					PDDocument doc = documents.get(i);
					doc.close();
				}
			}
		}
	}

	private static void writeDocument(PDDocument doc, String fileName) throws IOException {
		FileOutputStream output = null;
		COSWriter writer = null;
		try {
			output = new FileOutputStream(fileName);
			writer = new COSWriter(output);
			writer.write(doc);
		} finally {
			if (output != null) {
				output.close();
			}
			if (writer != null) {
				writer.close();
			}
		}
	}

	/**
	 * This will print the usage requirements and exit.
	 */
	private static void usage() {
		String message = "Usage: java -jar pdfbox-app-x.y.z.jar PDFSplit [options] <inputfile>\n" + "\nOptions:\n"
				+ "  -password  <password>  : Password to decrypt document\n"
				+ "  -split     <integer>   : split after this many pages (default 1, if startPage and endPage are unset)\n"
				+ "  -startPage <integer>   : start page\n" + "  -endPage   <integer>   : end page\n"
				+ "  -outputPrefix <prefix> : Filename prefix for splitted files\n"
				+ "  <inputfile>            : The PDF document to use\n";

		System.err.println(message);
		System.exit(1);
	}
}