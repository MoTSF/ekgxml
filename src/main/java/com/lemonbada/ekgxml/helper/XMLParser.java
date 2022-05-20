package com.lemonbada.ekgxml.helper;

import com.lemonbada.ekgxml.dto.TaskProcess;
import com.lemonbada.ekgxml.model.RestingECG;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;

@Component
public class XMLParser {

    public TaskProcess.ParseResult parse(File xmlFile) {

        TaskProcess.ParseResult resultDto =
                TaskProcess.ParseResult.builder()
                        .path(xmlFile.toPath())
                        .success(true).build();

        try {

            String xmlContent = String.join(System.lineSeparator(), Files.readAllLines(xmlFile.toPath()));
            xmlContent = stripNonValidXMLCharacters(xmlContent);

            JAXBContext jaxbContext = JAXBContext.newInstance(RestingECG.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
            xmlInputFactory.setProperty(javax.xml.XMLConstants.ACCESS_EXTERNAL_DTD, "all");

            XMLStreamReader xmlStreamReader =
                    xmlInputFactory.createXMLStreamReader(new StringReader(xmlContent));

             resultDto.setRestingECG ((RestingECG)unmarshaller.unmarshal(xmlStreamReader));
             xmlStreamReader.close();

             return resultDto;


        } catch(UnmarshalException e) {
            resultDto.setSuccess(false);
            resultDto.setErrorMessage(e.getMessage());
            if(e.getLinkedException() != null)
                resultDto.setErrorMessage(e.getLinkedException().getMessage());
            return resultDto;

        }  catch (XMLStreamException | JAXBException | IOException e) {
            resultDto.setSuccess(false);
            resultDto.setErrorMessage(e.getMessage());
            return resultDto;
        }
    }


    public String stripNonValidXMLCharacters(String in) {
        StringBuffer out = new StringBuffer();
        char current;
        if (in == null || ("".equals(in))) return "";
        for (int i = 0; i < in.length(); i++) {
            current = in.charAt(i);
            if ((current == 0x9) ||
                    (current == 0xA) ||
                    (current == 0xD) ||
                    ((current >= 0x20) && (current <= 0xD7FF)) ||
                    ((current >= 0xE000) && (current <= 0xFFFD)) ||
                    ((current >= 0x10000) && (current <= 0x10FFFF)))
                out.append(current);
        }
        return out.toString();
    }
}
