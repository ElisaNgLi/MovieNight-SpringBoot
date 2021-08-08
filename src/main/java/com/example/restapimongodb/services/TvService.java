package com.example.restapimongodb.services;

import com.example.restapimongodb.models.Movie;
import com.example.restapimongodb.models.Tv;
import com.example.restapimongodb.models.TvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TvService {

    @Autowired
    private TvRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Tv> getTv(){
        return repository.findAll();
    }

    public Optional<Tv> getTvByID(int id) throws Exception {
        Optional<Tv> tv = repository.findById(id);
        if(!tv.isPresent()){
            throw new Exception("Tv Show " + id + " not found");
        }
        return tv;
    }

    public List<Tv> getTitleTv(String title){
        Query query = new Query();
        query.addCriteria(Criteria.where("title").is(title));
        List<Tv> tv = mongoTemplate.find(query, Tv.class);
        return tv;
    }

    public List<Tv> getFeatureTv(Boolean feature){

        Query query = new Query();
        query.addCriteria(Criteria.where("feature").is(true));
        List<Tv> tv = mongoTemplate.find(query, Tv.class);
        return tv;
    }

    public void addTv(Tv tv){
        repository.insert(tv);
    }

    public Tv updateTv(int id, Tv newTv) throws Exception {
        Optional<Tv> tv = repository.findById(id);
        if(!tv.isPresent()){
            throw new Exception("Tv Show " + id + " not found");
        }
        tv.get().setBuy(newTv.getBuy());
        tv.get().setBackdrop_path(newTv.getBackdrop_path());
        tv.get().setFeature(newTv.getFeature());
        tv.get().setGenre(newTv.getGenre());
        tv.get().setOverview(newTv.getOverview());
        tv.get().setPoster_path(newTv.getPoster_path());
        tv.get().setRating(newTv.getRating());
        tv.get().setRelease_date(newTv.getRelease_date());
        tv.get().setTitle(newTv.getTitle());
        tv.get().setType(newTv.getType());
        tv.get().setRent(newTv.getRent());

        Tv updatedTvShow = repository.save(tv.get());
        return updatedTvShow;
    }

    public Optional<Tv> deleteTv(int id) throws Exception{
        Optional<Tv> tv = repository.findById(id);
        if(!tv.isPresent()){
            throw new Exception("Tv Show " + id + " not found");
        }

        repository.deleteById(id);
        return tv;
    }
}
