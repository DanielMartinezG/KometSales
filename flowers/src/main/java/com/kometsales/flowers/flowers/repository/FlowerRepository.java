package com.kometsales.flowers.flowers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kometsales.flowers.flowers.entity.Flower;

public interface FlowerRepository extends JpaRepository<Flower,Integer>{

}
