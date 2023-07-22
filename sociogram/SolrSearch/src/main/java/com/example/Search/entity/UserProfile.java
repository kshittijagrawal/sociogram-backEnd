package com.example.Search.entity;

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
@SolrDocument(solrCoreName = "searchUserProfile")
@Component
public class UserProfile {

    @Id
    @Indexed(name = "id", type = "string")
    private String userId;

    @Indexed(name = "name", type = "string")
    private String username;

    @Indexed(name="subject", type = "string")
    private String bio;

    @Indexed(name="url", type = "string")
    private String imageURL;

}
