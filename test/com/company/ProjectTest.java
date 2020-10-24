package com.company;

import com.company.controller.DeviceReader;
import com.company.controller.RequestReader;
import com.company.controller.RequestWriter;
import com.company.model.Device;
import org.junit.Test;
import org.hamcrest.core.IsNull;
import org.junit.runner.Request;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProjectTest {

    @Test
    public void test_read_device() {
        DeviceReader dvcReader=new DeviceReader();

        Map<Integer, Device> result =  dvcReader.read();

        assertThat(result, IsNull.notNullValue());
        assertThat(result.get(0).hasSecondaryPort(), is(true));
    }

    @Test
    public void test_write() throws Exception {
        RequestWriter wrt=new RequestWriter();

        String output = String.format("%d,%d,%d,%d", 0, 0, 1, 1);

        List<String> listCsv = new ArrayList();
        listCsv.add(output);
        wrt.write(listCsv);

        File tmpDir = new File("D:\\masa\\TelnyxTask\\output.csv");
        boolean exists = tmpDir.exists();

        assertTrue(exists);
    }

    @Test
    public void test_read_request() {
        RequestReader requestReader=new RequestReader();

        Map<Integer, Integer> result =  requestReader.read();

        assertThat(result, IsNull.notNullValue());
        assertThat(result.get(0), is(1));
        assertThat(result.keySet().size(),is(5));
    }
}
