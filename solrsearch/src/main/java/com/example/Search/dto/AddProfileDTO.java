package com.example.Search.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;
import org.springframework.stereotype.Component;

@Data
@JsonIgnoreProperties
@AllArgsConstructor
@NoArgsConstructor
public class AddProfileDTO {

    private String userId;

    private String username;

    private String bio;

    private String imageURL;

}

