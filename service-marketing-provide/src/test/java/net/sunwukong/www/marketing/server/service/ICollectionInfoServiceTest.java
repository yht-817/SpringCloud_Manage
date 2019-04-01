package net.sunwukong.www.marketing.server.service;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class ICollectionInfoServiceTest {
    @Autowired
    ICollectionInfoService iCollectionInfoService;
    /*public void addUserCollectionInfoPage() throws Exception {
        CollectionInfo collectionInfo = new CollectionInfo();
        collectionInfo.setCollectionType("aaaaaa");
        collectionInfo.setContentNo("bbb");
        collectionInfo.setUserNo("ccccc");
        ResponseData responseData = iCollectionInfoService.addUserCollectionInfoPage(collectionInfo);
        System.out.println(responseData.toString());

    }*/

}