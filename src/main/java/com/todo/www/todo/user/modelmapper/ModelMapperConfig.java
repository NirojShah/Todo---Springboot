package com.todo.www.todo.user.modelmapper;

import com.todo.www.todo.user.UserEntity;
import com.todo.www.todo.user.dto.UpdaetUserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setSkipNullEnabled(true); // ✅ only update non-null fields

        // ✅ skip restricted fields
        mapper.typeMap(UpdaetUserDto.class, UserEntity.class)
                .addMappings(m -> {
                    m.skip(UserEntity::setId);
                    m.skip(UserEntity::setPassword);
                });

        return mapper;
    }
}