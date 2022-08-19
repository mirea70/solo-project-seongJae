package api.v1.test;


import api.v1.Bz_Controller;
import api.v1.Bz_Service;
import api.v1.Member_Dto;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static api.v1.util.ApiDocumentUtils.*;

@WebMvcTest(Bz_Controller.class)
//@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class Bz_Controller_Test {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private Bz_Service bz_service;

    @Test
    public void All_Search_Test() throws Exception {
        // request - 필요X
        // response

        Member_Dto response1 = Member_Dto.builder()
                .name("홍길동1")
                .sex("m")
                .company_name("나라사랑")
                .company_type("005")
                .company_location("001")
                .build();

        Member_Dto response2 = Member_Dto.builder()
                .name("김민영2")
                .sex("w")
                .company_name("우리나라")
                .company_type("003")
                .company_location("002")
                .build();

        List<Member_Dto> list = List.of(response2, response1);
        // Stubbing
        given(bz_service.find_All()).willReturn(list);
        // when
        ResultActions actions = mockMvc.perform(
                get("/search")
                        .accept(MediaType.APPLICATION_JSON));
        // then
        actions.andExpect(status().isOk())
                .andDo(document(
                        "get-All-Members",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        responseFields(
                                List.of(
                                        fieldWithPath("list").type(JsonFieldType.ARRAY).description("회원 전체 리스트"),
                                        fieldWithPath("list[].name").type(JsonFieldType.STRING).description("회원 이름"),
                                        fieldWithPath("list[].sex").type(JsonFieldType.STRING).description("회원 성별"),
                                        fieldWithPath("list[].company_name").type(JsonFieldType.STRING).description("회사 이름"),
                                        fieldWithPath("list[].company_type").type(JsonFieldType.STRING).description("회사 업종코드"),
                                        fieldWithPath("list[].company_location").type(JsonFieldType.STRING).description("회사 위치코드")
                                )
                        )
                ));
    }

    @Test
    public void if_location_Test() throws Exception {
        // request
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("company_location", "3");
        // response
        Member_Dto response1 = Member_Dto.builder()
                .name("홍길동1")
                .sex("m")
                .company_name("가리봉동")
                .company_type("005")
                .company_location("003")
                .build();

        Member_Dto response2 = Member_Dto.builder()
                .name("김민영2")
                .sex("w")
                .company_name("밀지")
                .company_type("003")
                .company_location("030")
                .build();

        List<Member_Dto> list = List.of(response2, response1);
        // Stubbing
        given(bz_service.findIf_location(Mockito.anyString())).willReturn(list);
        // when
        ResultActions actions = mockMvc.perform(
                get("/if_location")
                        .params(queryParams)
                        .accept(MediaType.APPLICATION_JSON)
        );
        // then
        actions.andExpect(status().isOk())
                .andDo(
                        document("get-if-location",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                requestParameters(
                                        List.of(parameterWithName("company_location").description("회사 지역코드 검색키워드"))
                                ),
                        responseFields(
                                List.of(
                                        fieldWithPath("list").type(JsonFieldType.ARRAY).description("회원 전체 리스트"),
                                        fieldWithPath("list[].name").type(JsonFieldType.STRING).description("회원 이름"),
                                        fieldWithPath("list[].sex").type(JsonFieldType.STRING).description("회원 성별"),
                                        fieldWithPath("list[].company_name").type(JsonFieldType.STRING).description("회사 이름"),
                                        fieldWithPath("list[].company_type").type(JsonFieldType.STRING).description("회사 업종코드"),
                                        fieldWithPath("list[].company_location").type(JsonFieldType.STRING).description("회사 위치코드")
                                )
                        )
                    )
                );
    }

    @Test
    public void if_type_Test() throws Exception {
// request
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("company_type", "7");
        // response
        Member_Dto response1 = Member_Dto.builder()
                .name("홍길동1")
                .sex("m")
                .company_name("미련한 가슴아")
                .company_type("007")
                .company_location("010")
                .build();

        Member_Dto response2 = Member_Dto.builder()
                .name("김민영2")
                .sex("w")
                .company_name("고독하다")
                .company_type("705")
                .company_location("100")
                .build();

        List<Member_Dto> list = List.of(response2, response1);
        // Stubbing
        given(bz_service.findIf_type(Mockito.anyString())).willReturn(list);
        // when
        ResultActions actions = mockMvc.perform(
                get("/if_type")
                        .params(queryParams)
                        .accept(MediaType.APPLICATION_JSON)
        );
        // then
        actions.andExpect(status().isOk())
                .andDo(
                        document("get-if-type",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                requestParameters(
                                        List.of(parameterWithName("company_type").description("회사 업종코드 검색키워드"))
                                ),
                                responseFields(
                                        List.of(
                                                fieldWithPath("list").type(JsonFieldType.ARRAY).description("회원 전체 리스트"),
                                                fieldWithPath("list[].name").type(JsonFieldType.STRING).description("회원 이름"),
                                                fieldWithPath("list[].sex").type(JsonFieldType.STRING).description("회원 성별"),
                                                fieldWithPath("list[].company_name").type(JsonFieldType.STRING).description("회사 이름"),
                                                fieldWithPath("list[].company_type").type(JsonFieldType.STRING).description("회사 업종코드"),
                                                fieldWithPath("list[].company_location").type(JsonFieldType.STRING).description("회사 위치코드")
                                        )
                                )
                        )
                );
    }

}
