package com.ymatou.alarmcenter.facade.rest;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by zhangxiaoming on 2016/11/29.
 */

@Component("systemResource")
@Path("/")
@Produces(MediaType.TEXT_HTML)
public class SystemResourceImpl implements SystemResource {

    @GET
    @Path("/version")
    @Override
    public String version() {
        return "2016-12-16.2";
    }


    @Override
    @GET
    @Path("/warmup")
    public String status() {

        return "ok";
    }

    @Override
    @GET
    @Path("/index")
    public String index() {
        return "ok";
    }
}
