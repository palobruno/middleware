import java.io.File;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.integration.xml.transformer.AbstractXmlTransformer;
import org.springframework.integration.xml.transformer.XsltPayloadTransformer;

public class XMLCSVTransformer extends AbstractXmlTransformer {
	@Override
	protected Message doTransform(Message message) throws Exception {
		String payload = message.getPayload();

		Source xslt = new StreamSource(new File("xmltocsv.xslt"));
		Source text = new StreamSource(new File("pagos.xml"));
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer(xslt);
StreamResult sr = new StreamResult();
		transformer.transform(text, sr);

		Message aRetornar = new Message();
		aRetornar.setPayload(sr);

	}
}