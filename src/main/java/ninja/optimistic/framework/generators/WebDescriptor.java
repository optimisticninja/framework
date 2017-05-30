package ninja.optimistic.framework.generators;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WebDescriptor {
	private static final String OUTPUT_DIR = "src/main/webapp/WEB-INF/";
	private static final String OUTPUT_FILE = "web.xml";
	public static final String OUTPUT_FULL_PATH = OUTPUT_DIR + OUTPUT_FILE;

	public String generateWebDescriptor(String description, String apiPackage, String path) {
		Document doc = null;

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.newDocument();

			Element webApp = doc.createElement("web-app");
			webApp.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			webApp.setAttribute("xmlns", "http://java.sun.com/xml/ns/javaee");
			webApp.setAttribute("xsi:schemaLocation",
					"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd");
			webApp.setAttribute("id", "WebApp_ID");
			webApp.setAttribute("version", "3.0");
			{
				Element displayName = doc.createElement("display-name");
				displayName.setTextContent(description);
				webApp.appendChild(displayName);
				Element servlet = doc.createElement("servlet");
				{
					Element servletName = doc.createElement("servlet-name");
					servletName.setTextContent(description);
					Element servletClass = doc.createElement("servlet-class");
					servletClass.setTextContent("org.glassfish.jersey.servlet.ServletContainer");
					servlet.appendChild(servletName);
					servlet.appendChild(servletClass);

					Element initParam1 = doc.createElement("init-param");
					{
						Element paramName1 = doc.createElement("param-name");
						paramName1.setTextContent("jersey.config.server.provider.packages");
						Element paramValue1 = doc.createElement("param-value");
						paramValue1.setTextContent(apiPackage + ",com.jersey.jaxb,com.fasterxml.jackson.jaxrs.json");
						initParam1.appendChild(paramName1);
						initParam1.appendChild(paramValue1);
						servlet.appendChild(initParam1);
					}
					
					Element loadOnStartup = doc.createElement("load-on-startup");
					loadOnStartup.setTextContent("1");
					servlet.appendChild(loadOnStartup);
					webApp.appendChild(servlet);
				}
				Element servletMapping = doc.createElement("servlet-mapping");
				{
					Element servletName = doc.createElement("servlet-name");
					servletName.setTextContent(description);
					Element pattern = doc.createElement("url-pattern");
					pattern.setTextContent("/" + path + "/*");
					servletMapping.appendChild(servletName);
					servletMapping.appendChild(pattern);
					webApp.appendChild(servletMapping);
				}
				doc.appendChild(webApp);
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);

			File directory = new File(OUTPUT_DIR);
			if (!directory.exists()) {
				directory.mkdirs();
			}

			StreamResult result = new StreamResult(new File(OUTPUT_DIR + OUTPUT_FILE));
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return doc.toString();
	}
}
