/*
 * WSO2 API Manager - Publisher API
 * This specifies a **RESTful API** for WSO2 **API Manager** - Publisher.  Please see [full swagger definition](https://raw.githubusercontent.com/wso2/carbon-apimgt/v6.0.4/components/apimgt/org.wso2.carbon.apimgt.rest.api.publisher/src/main/resources/publisher-api.yaml) of the API which is written using [swagger 2.0](http://swagger.io/) specification. 
 *
 * OpenAPI spec version: 0.10.0
 * Contact: architecture@wso2.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package org.wso2.carbon.apimgt.rest.integration.tests.api.publisher;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wso2.carbon.apimgt.rest.integration.tests.publisher.ApiResponse;
import org.wso2.carbon.apimgt.rest.integration.tests.publisher.api.DocumentCollectionApi;
import org.wso2.carbon.apimgt.rest.integration.tests.publisher.api.DocumentIndividualApi;
import org.wso2.carbon.apimgt.rest.integration.tests.publisher.api.APICollectionApi;
import org.wso2.carbon.apimgt.rest.integration.tests.publisher.api.APIIndividualApi;
import org.wso2.carbon.apimgt.rest.integration.tests.publisher.ApiException;
import org.wso2.carbon.apimgt.rest.integration.tests.publisher.model.*;

import java.io.File;
import java.util.ArrayList;


/**
 * API tests for DocumentIndividualApi
 */
public class DocumentIndividualApiIT {

    private final DocumentIndividualApi api = new DocumentIndividualApi();
    private final DocumentCollectionApi docApi = new DocumentCollectionApi();
    private final APICollectionApi apiSetup = new APICollectionApi();
    private final APIIndividualApi apiIndividualApi = new APIIndividualApi();
    private final TestUtils testUtils = new TestUtils();

    private String APIID = null;
    private String DOCID = null;

    @BeforeClass
    public void beforeClass() throws ApiException {

        // Create an API for testing
        API body = new API();
        String ifMatch = null;
        String ifUnmodifiedSince = null;

        body.setName("DocsAPII");
        body.setContext("docssi");
        body.setVersion("1.0.0");
        body.setProvider("admin");
        body.setLifeCycleStatus("CREATED");
        body.setTransport(new ArrayList<String>() {{
            add("http");
        }});
        body.setCacheTimeout(100);
        body.setPolicies(new ArrayList<String>() {{
            add("Unlimited");
        }});
        body.setVisibility(API.VisibilityEnum.PUBLIC);
        body.setTags(new ArrayList<String>());
        body.setVisibleRoles(new ArrayList<String>());
        body.setVisibleTenants(new ArrayList<String>());
        body.setSequences(new ArrayList<Sequence>());
        body.setBusinessInformation(new APIBusinessInformation());
        body.setCorsConfiguration(new APICorsConfiguration());
        API response = apiSetup.apisPost(body);
        APIID = response.getId();


        Document docBody = new Document();
        docBody.setName("Help");
        docBody.setType(Document.TypeEnum.HOWTO);
        docBody.setSummary("This is the summary.");
        docBody.setSourceType(Document.SourceTypeEnum.INLINE);
        docBody.setVisibility(Document.VisibilityEnum.API_LEVEL);
        docBody.setInlineContent("This is the inline content");
        Document docResponse = docApi.apisApiIdDocumentsPost(response.getId(), docBody, ifMatch,ifUnmodifiedSince);
        DOCID = docResponse.getDocumentId();
    }

    /**
     * Get the content of an API document
     * <p>
     * This operation can be used to retrive the content of an API&#39;s document.  The document can be of 3 types. In each cases responses are different.  1. **Inline type**:    The content of the document will be retrieved in &#x60;text/plain&#x60; content type 2. **FILE type**:    The file will be downloaded with the related content type (eg. &#x60;application/pdf&#x60;) 3. **URL type**:     The client will recieve the URL of the document as the Location header with the response with - &#x60;303 See Other&#x60;
     *
     * @throws ApiException if the Api call fails
     */

    /**
     *  please refer https://github.com/wso2/product-apim/issues/1575
     * due to this issue response cannot be taken to be compared. Therefore disabling this test until the issue is fixed.
     *
     **/
    @Test(enabled = false)
    public void apisApiIdDocumentsDocumentIdContentGetTest() throws ApiException {
        String apiId = APIID;
        String documentId = DOCID;
        String ifNoneMatch = null;
        String ifModifiedSince = null;
        api.apisApiIdDocumentsDocumentIdContentGet(apiId, documentId, ifNoneMatch, ifModifiedSince);

    }

    /**
     * Upload the content of an API document
     * <p>
     * Thid operation can be used to upload a file or add inline content to an API document.  **IMPORTANT:** * Either **file** or **inlineContent** form data parameters should be specified at one time. * Document&#39;s source type should be **FILE** in order to upload a file to the document using **file** parameter. * Document&#39;s source type should be **INLINE** in order to add inline content to the document using **inlineContent** parameter.
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void apisApiIdDocumentsDocumentIdContentPostTest() throws ApiException {
        String apiId = APIID;
        String documentId = DOCID;
        File file = new File(getClass().getResource("/file1.pdf").getFile());
        String inlineContent = "The content";
        String ifMatch = null;
        String ifUnmodifiedSince = null;
        Document response = api.apisApiIdDocumentsDocumentIdContentPost(apiId, documentId, file, null, ifMatch, ifUnmodifiedSince);
        System.out.println(response);
       // Assert.assertEquals(response.getInlineContent(), inlineContent, "inline content update mismatch");
    }

    /**
     * Delete a document of an API
     * <p>
     * This operation can be used to delete a document associated with an API.
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void apisApiIdDocumentsDocumentIdDeleteTest() throws ApiException {
        String ifNoneMatch = null;
        String ifMatch = null;
        String ifUnmodifiedSince = null;

        String apiId = testUtils.createApi("API-150", "1.0.0", "API-150");
        Document docBody = new Document();
        docBody.setName("Help");
        docBody.setType(Document.TypeEnum.HOWTO);
        docBody.setSummary("This is the summary.");
        docBody.setSourceType(Document.SourceTypeEnum.INLINE);
        docBody.setVisibility(Document.VisibilityEnum.API_LEVEL);
        Document docResponse = docApi.apisApiIdDocumentsPost(apiId, docBody, ifMatch,ifUnmodifiedSince);
        String documentId = docResponse.getDocumentId();

        api.apisApiIdDocumentsDocumentIdDelete(apiId, documentId, ifMatch, ifUnmodifiedSince);
        DocumentList response = docApi.apisApiIdDocumentsGet(apiId, 10, 0, ifNoneMatch);
        int documentCount = response.getCount();
        Assert.assertEquals(documentCount, 0, "document count mismatch");
    }

    /* FAILS
   * Please refer
   * https://github.com/wso2/product-apim/issues/1617
   * Therefore making the test disabled.
   */
    @Test(enabled = false)
    public void apisApiIdDocumentsDocumentIdDeleteTest_NF_invalidDocument() throws ApiException {
        String apiId = APIID;
        String documentId = "invalidDocID";
        String ifMatch = null;
        String ifUnmodifiedSince = null;
       try {
            api.apisApiIdDocumentsDocumentIdDelete(apiId, documentId, ifMatch, ifUnmodifiedSince);
       }
       catch (ApiException ae)
        {
            int responseCode = ae.getCode();
            Assert.assertEquals(responseCode, 404, "Response code mismatch");

        }

    }

    /*FAILS
    * Please refer
    * https://github.com/wso2/product-apim/issues/1617
    * Therefore making the test disabled.
    */
    @Test(enabled = false)
    public void apisApiIdDocumentsDocumentIdDeleteTest_NF_invalidAPI() throws ApiException {
        String apiId = "invalidApi";
        String documentId = DOCID;
        String ifMatch = null;
        String ifUnmodifiedSince = null;
        try {
            api.apisApiIdDocumentsDocumentIdDelete(apiId, documentId, ifMatch, ifUnmodifiedSince);
        }
        catch (ApiException ae)
        {
            int responseCode = ae.getCode();
            Assert.assertEquals(responseCode, 404, "Response code mismatch");

        }

    }

    /**
     * Get a document of an API
     * <p>
     * This operation can be used to retrieve a particular document&#39;s metadata associated with an API.
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void apisApiIdDocumentsDocumentIdGetTest() throws ApiException {
        String apiId = APIID;
        String documentId = DOCID;
        String ifNoneMatch = null;
        String ifModifiedSince = null;
        Document response = api.apisApiIdDocumentsDocumentIdGet(apiId, documentId, ifNoneMatch, ifModifiedSince);
        System.out.println(response);
        Assert.assertEquals(response.getName(), "Help", "Document name mismatch");
        Assert.assertEquals(response.getVisibility().toString(), "API_LEVEL", "Document visibility mismatch");
        Assert.assertEquals(response.getSourceType().toString(), "INLINE", "Document visibility mismatch");
        Assert.assertEquals(response.getType().toString(), "HOWTO", "Document visibility mismatch");
        Assert.assertEquals(response.getSummary().toString(), "This is the summary.", "Document summary mismatch");
    }

    @Test
    public void apisApiIdDocumentsDocumentIdGetTest_NF() throws ApiException {
        String apiId = APIID;
        String documentId = "invalidDocID";
        String ifNoneMatch = null;
        String ifModifiedSince = null;
        try {
            api.apisApiIdDocumentsDocumentIdGet(apiId, documentId, ifNoneMatch, ifModifiedSince);
        }
        catch (ApiException ae)
        {
            int responseCode = ae.getCode();
            JsonParser parser= new JsonParser();
            JsonObject responseBody = (JsonObject) parser.parse(ae.getResponseBody());
            String errorMsg = responseBody.get("message").getAsString();
            System.out.println(errorMsg);

            Assert.assertEquals(responseCode, 404, "Response code mismatch");
            Assert.assertEquals(errorMsg, "Documentation not found.", "Response message mismatch");
        }
    }

    /**
     * Update a document of an API
     * <p>
     * This operation can be used to update metadata of an API&#39;s document.
     *
     * TODO - Check if the usage is correct.
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void apisApiIdDocumentsDocumentIdPutTest() throws ApiException {
        String apiId = APIID;
        String documentId = DOCID;
        Document body = new Document();
        body.setSourceType(Document.SourceTypeEnum.FILE);
        body.setType(Document.TypeEnum.SWAGGER_DOC);
        body.setVisibility(Document.VisibilityEnum.OWNER_ONLY);
        body.setSummary("Summary changed");
        String ifMatch = null;
        String ifUnmodifiedSince = null;
        Document response = api.apisApiIdDocumentsDocumentIdPut(apiId, documentId, body,ifMatch, ifUnmodifiedSince);

        Assert.assertEquals(response.getType().toString(),"SWAGGER_DOC", "Type update fails");
        Assert.assertEquals(response.getSummary().toString(),"Summary changed", "Summary update fails");
        Assert.assertEquals(response.getSourceType().toString(),"FILE", "Source Type update fails");
        Assert.assertEquals(response.getVisibility().toString(),"OWNER_ONLY", "Visibility update fails");
    }

    @AfterClass
    public void afterClass() throws ApiException {
        APIList response = apiSetup.apisGet(10, 0, null, null);
        for (int i=0 ; i< response.getCount(); i++)
        {
            apiIndividualApi.apisApiIdDelete(response.getList().get(i).getId(), null, null);
        }
    }
}
