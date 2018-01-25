package com.canaan.core.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.annotation.Resource;

import org.dozer.Mapper;
import org.dozer.MappingException;

public class CollectionMapperDecorator {
	@Resource
	private Mapper baseMapper;


	public <T> Collection<T> mapCollection(Object[] source, Class<T> destinationClass) {
		return mapCollection(Arrays.asList(source), destinationClass);
    }
	public <T> Collection<T> mapCollection(Object[] source, Collection<T> destination, Class<T> destinationClass) {
		return mapCollection(Arrays.asList(source), destination, destinationClass);
    }

	public <T> Collection<T> mapCollection(Collection<? extends Object> source, Class<T> destinationClass) {      
		return mapCollection(source, null, destinationClass);
    }   

	public <T> Collection<T> mapCollection(Collection<? extends Object> source, Collection<T> destination, Class<T> destinationClass) {
      if(destination == null) {
         destination = new ArrayList<T>();
      }
      for(Object sourceObj : source) {
         destination.add(map(sourceObj, destinationClass));
      }

      return destination;      
    }

	public <T> T map(Object source, Class<T> destinationClass, String mapId) throws MappingException {
		return baseMapper.map(source, destinationClass, mapId);
    }

	public <T> T map(Object source, Class<T> destinationClass) throws MappingException {

		return baseMapper.map(source, destinationClass);
    }

	public void map(Object source, Object destination, String mapId) throws MappingException {
		baseMapper.map(source, destination, mapId);
    }

	public void map(Object source, Object destination) throws MappingException {
		baseMapper.map(source, destination);
    }
}