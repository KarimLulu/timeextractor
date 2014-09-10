package com.codeminders.labs.timeextractor.rest;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.codeminders.labs.timeextractor.constants.RestParameters;
import com.codeminders.labs.timeextractor.entities.AnnotationIntervalHtml;
import com.codeminders.labs.timeextractor.service.SUTimeService;

/* Rest service to extract temporal date from html page*/

@Path("/html")
public class TimeExtractorFromHtmlRestService {

    private static SUTimeService sutimeService = new SUTimeService();

    @POST
    @Path("/annotate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAnnotationsForMultipleTexts(JSONArray jsonArray) throws JSONException {
        JSONObject object = jsonArray.getJSONObject(0);
        String html = object.optString(RestParameters.HTML);
        Map<String, List<AnnotationIntervalHtml>> result = sutimeService.extractDatesAndTimeFromHtml(html);
        Response response = Response.status(200).entity(result).build();
        System.out.println(result);
        return response;
    }

}