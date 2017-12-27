

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.dozer.Mapper;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.canaan.jgsf.util.CollectionMapperDecorator;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:configs/spring-common.xml"})
@Slf4j
public class DozerTest {

	@Resource
	private Mapper baseMapper;
	@Resource
	private CollectionMapperDecorator collectionMapper;
	
	@Test
	public void testDozer() {
		DozerOneDTO one = new DozerOneDTO();
		one.setAddress("青岛市李沧区黑龙江路227号");
		one.setAge(22);
		one.setBirth(new DateTime().toDate());
		one.setId(1L);
		one.setName("xxx");
		DozerTwoDTO two = baseMapper.map(one, DozerTwoDTO.class);
		List<DozerOneDTO> list = new ArrayList<>(1);
		list.add(one);
		List<DozerTwoDTO> dtoList = new ArrayList<DozerTwoDTO>(collectionMapper.mapCollection(list, DozerTwoDTO.class));
		log.info("dozer list==============================" + dtoList.toString());
		log.info("dozer bean==============================" + two.toString());
	}
}
