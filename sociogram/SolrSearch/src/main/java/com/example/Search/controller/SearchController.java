package com.example.Search.controller;

import com.example.Search.dto.AddProfileDTO;
import com.example.Search.entity.UserProfile;
import com.example.Search.repository.UserProfileRepository;
import com.example.Search.utils.Constants;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/search")
public class SearchController {


    @Autowired
    SolrClient solrClient;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    SolrTemplate solrTemplate;

    @GetMapping("/query/{userName}")
    public ResponseEntity<List<UserProfile>> searchUser(@PathVariable("userName") String userName) throws IOException, SolrServerException {
        SolrClient solrClient = new HttpSolrClient.Builder("http://localhost:8983/solr/searchUser").build();        //create a solrclient for solr core(collection)
        SolrQuery solrQuery = new SolrQuery();          //create solr query

        String userNameMatch="";
        for (int i=0;i<userName.length();i++){
            if(userName.charAt(i)==' ')
            {
                continue;
            }
            userNameMatch+=userName.charAt(i)+"*";
        }
        solrQuery.setQuery("name:*"+userNameMatch);       //set query for search on product name
//        solrQuery.setParam("0", productName);
        QueryResponse response = solrClient.query(solrQuery);
//        System.out.println(response);
//        Object solrDocument = response.getResults().toArray();
//        System.out.println(solrDocument);

        List<UserProfile> userProfileList = new ArrayList<>();
        for(SolrDocument solrDocument1: response.getResults()){
            UserProfile userProfile = new UserProfile();
            userProfile.setUserId(solrDocument1.getFieldValue("id").toString());
            userProfile.setUsername(solrDocument1.getFieldValue("name").toString());
            if(solrDocument1.getFieldValue("subject")==null) {
                userProfile.setBio("");
            }
            else {
                userProfile.setBio(solrDocument1.getFieldValue("subject").toString());

            }
            userProfile.setImageURL(solrDocument1.getFieldValue("url").toString());
            userProfileList.add(userProfile);
        }
        return new ResponseEntity<List<UserProfile>>(userProfileList,HttpStatus.OK);
    }



    @PostMapping("/addProfile")
    public ResponseEntity<String> addProfile(@RequestBody AddProfileDTO addProfileDTO) {

//        UserProfile userProfile = new UserProfile();
//        userProfile = userProfileRepository.findById(addProfileDTO.getUserId()).get();
//        userProfile.setBio(addProfileDTO.getBio());
//        userProfile.setImageURL(addProfileDTO.getImageURL());
//        UserProfile userProfile1 = userProfileRepository.save(userProfile);
//
//        if(userProfile1==null){
//            return new ResponseEntity<>("Something went wrong", HttpStatus.OK);
//        }
//        else{
//            return new ResponseEntity<>("Success", HttpStatus.OK);
//        }


        SolrInputDocument updateDoc = new SolrInputDocument();
        updateDoc.addField("id", addProfileDTO.getUserId());
        updateDoc.addField("subject", addProfileDTO.getBio());
        updateDoc.addField("url", addProfileDTO.getImageURL());
        updateDoc.addField("name", addProfileDTO.getUsername());

        try {
            solrClient.add(updateDoc);
            solrClient.commit();
            return new ResponseEntity<String>("Success", HttpStatus.OK);
        } catch (SolrServerException | IOException e) {
            return new ResponseEntity<String>(""+e.getMessage(), HttpStatus.BAD_REQUEST);
        }




    }

    @PostMapping("/insertSearchUser")
    public ResponseEntity<String> insertUser(@RequestParam("userId") String userId, @RequestParam("username") String username) {
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", userId);
        doc.addField("name", username);
        doc.addField("url", Constants.imageProfileDefault);

        try {
            solrClient.add(doc);
            solrClient.commit();
            return new ResponseEntity<String>("Success", HttpStatus.OK);
        } catch (SolrServerException | IOException e) {
            return new ResponseEntity<String>(""+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}