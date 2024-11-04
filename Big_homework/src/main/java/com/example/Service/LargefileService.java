package com.example.Service;

import com.example.entity.Largefile;

public interface LargefileService {
    public Integer add(Largefile largefile);

    public Largefile findOne(String id);


    int delete(String id);
}
