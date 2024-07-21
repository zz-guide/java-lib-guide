package org.zz.lib.guide.easyexcel.test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;


import org.junit.jupiter.api.Test;
import org.zz.lib.guide.easyexcel.pojo.DemoData;
import org.zz.lib.guide.easyexcel.util.TestFileUtil;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestReadExcel {

    @Test
    public void testReadExcel(){
        Logger logger = Logger.getLogger("testReadExcel");
        String fileName = TestFileUtil.getClassesPath() + "demo" + File.separator + "demo.xlsx";
        logger.log(Level.INFO, "读取 excel：{0}", new Object[]{fileName});

        // 使用默认的 PageReadListener， 不需要额外写Listener
        EasyExcel.read(fileName, DemoData.class, new PageReadListener<DemoData>(dataList -> {
            for (DemoData demoData : dataList) {
                logger.log(Level.INFO, "用户信息：{0}", new Object[]{demoData});
            }
        })).sheet().doRead();
    }

    @Test
    public void testReadExcel2(){
        Logger logger = Logger.getLogger("testReadExcel2");
        String fileName = TestFileUtil.getClassesPath() + "demo" + File.separator + "demo.xlsx";
        logger.log(Level.INFO, "读取 excel：{0}", new Object[]{fileName});

        // 使用 ReadListener interface 不需要额外写Listener
        EasyExcel.read(fileName, DemoData.class, new ReadListener<DemoData>() {
            public static final int BATCH_COUNT = 100;
            private List<DemoData> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

            @Override
            public void invoke(DemoData data, AnalysisContext context) {
                cachedDataList.add(data);
                if (cachedDataList.size() >= BATCH_COUNT) {
                    saveData();
                    // 存储完成清理 list
                    cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                saveData();
            }

            private void saveData() {
                logger.log(Level.INFO, "{0}条数据，开始存储数据库！", new Object[]{cachedDataList.size()});
                logger.log(Level.INFO, "存储数据库成功", new Object[]{});
            }
        }).sheet().doRead();
    }
}
