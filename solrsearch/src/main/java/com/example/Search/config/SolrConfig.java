package com.example.Search.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@Configuration
@EnableSolrRepositories(
        basePackages = "com.example.Search.repository",
        namedQueriesLocation = "classpath:application.properties")
@ComponentScan
public class SolrConfig {
    @Bean
    public SolrClient solrClient() {  // to create Solr Client to access the Solr Database
        HttpSolrClient solr = new HttpSolrClient.Builder("http://localhost:8983/solr/searchUser").build();
//        solr.setParser(new XMLResponseParser());
        return solr;
    }

    @Bean
    public SolrTemplate solrTemplate(SolrClient client) throws Exception {
        return new SolrTemplate(client);
    }
}


// @Configuration
// @EnableSolrRepositories(basePackages = {"com.inov8.solr.repository"})
// @ComponentScan
// public class SolrConfig {

//     @Bean
//     SolrTemplate solrTemplate() {
//         return new SolrTemplate(solrServerFactory());
//     }

//     @Bean
//     SolrClientFactory solrServerFactory() {

//         Credentials credentials = new UsernamePasswordCredentials("solr", "SolrRocks");
//         return new HttpSolrClientFactory(solrServer(), "merchant_core", credentials , "BASIC");
//     }

//     @Bean
//     SolrClient solrServer() {
//         return new HttpSolrClient("http://localhost:8983/solr");
//     }
// }
