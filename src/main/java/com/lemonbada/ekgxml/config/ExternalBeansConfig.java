package com.lemonbada.ekgxml.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemonbada.ekgxml.model.RestingECG;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@Deprecated
public class ExternalBeansConfig {


    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public ModelMapper staticObjectMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true);

        /*
        // RestingECG => ESDoc
        Converter<List<RestingECG.Diagnosis.DiagnosisStatement>, String> diagnosisStatementConverter =
                context -> context.getSource().stream()
                        .map(RestingECG.Diagnosis.DiagnosisStatement::getStmtText)
                        .collect(Collectors.joining(","));

        modelMapper.typeMap(RestingECG.class, ESDoc.class)
                .addMappings(mapping -> {
                    mapping
                            .using(diagnosisStatementConverter)
                            .map(source -> source.getDiagnosis().getDiagnosisStatements(),
                                    ((destination, value) -> {
                                        destination.getDiagnosis().getDiagnosisStatement().setStmtText((String) value);
                                    }));
                });
        */

        return modelMapper;
    }
}
