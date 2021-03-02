package io.daff.notes.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author daffupman
 * @since 2021/3/1
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class NoteVoMapperTest {

    @Resource
    private NoteMapper noteMapper;

    @Test
    public void testSelectAll() {
        noteMapper.selectAll().forEach(System.out::println);
    }

}
