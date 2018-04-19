package com.humesis.quiz.controller;


import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.humesis.quiz.QuizRestServiceApplication;
import com.humesis.quiz.request.OptionRequest;
import com.humesis.quiz.request.QuestionRequest;
import com.humesis.quiz.request.QuizRequest;
import com.humesis.quiz.service.QuizService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = QuizRestServiceApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class QuizControllerTest {
	
private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;
	
	@Autowired
	QuizService quizService;

	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void AtestCreateQuiz() throws Exception {
		QuizRequest qr = new QuizRequest();
		qr.setName("test quiz 1");
		qr.setDescription("this is a test quiz 1 ");
		Gson gson = new Gson();
		String request = gson.toJson(qr);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/quiz")
				.content(request)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.name").value("test quiz 1"))
		.andDo(print());
	}
	
	@Test
	public void BtestUpdateQuiz() throws Exception {
		QuizRequest qr = new QuizRequest();
		qr.setName("test quiz 1 edit");
		qr.setDescription("this is a test quiz 1 edit");
		Gson gson = new Gson();
		String request = gson.toJson(qr);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/quiz/1")
				.content(request)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.name").value("test quiz 1 edit"))
		.andDo(print());
	}

	@Test
	public void CtestCreateQuestion() throws Exception {
		QuestionRequest qr = new QuestionRequest();
		OptionRequest or1 = new OptionRequest();
		OptionRequest or2 = new OptionRequest();
		OptionRequest or3 = new OptionRequest();
		List<OptionRequest> ors = new ArrayList<OptionRequest>();
		or1.setOptionText("option 1");
		or1.setIsAnswer((byte) 0);
		ors.add(or1);
		or2.setOptionText("option 2");
		or2.setIsAnswer((byte) 1);
		ors.add(or2);
		or3.setOptionText("option 3");
		or3.setIsAnswer((byte) 0);
		ors.add(or3);
		qr.setQuestionText("this is question 1");
		qr.setTimeLimit(30);
		qr.setOptions(ors);
		Gson gson = new Gson();
		String request = gson.toJson(qr);
		
		System.out.println("question Request : "+ request);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/quiz/1/question")
				.content(request)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.questionText").value("this is question 1"))
		.andDo(print());
		
	}
	
	@Test
	public void DtestUpdateQuestion() throws Exception {
		QuestionRequest qr = new QuestionRequest();
		qr.setQuestionText("this is question 1 edit");
		qr.setTimeLimit(60);
		Gson gson = new Gson();
		String request = gson.toJson(qr);
		mockMvc.perform(MockMvcRequestBuilders.put("/question/1")
				.content(request)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.questionText").value("this is question 1 edit"))
		.andDo(print());
	}

	@Test
	public void EtestAddOption() throws Exception {
		OptionRequest or1 = new OptionRequest();
		or1.setOptionText("option 4");
		or1.setIsAnswer((byte) 0);
		Gson gson = new Gson();
		String request = gson.toJson(or1);
		mockMvc.perform(MockMvcRequestBuilders.post("/question/1/option")
				.content(request)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.optionText").value("option 4"))
		.andDo(print());
	}

	@Test
	public void FtestEditOption() throws Exception {
		OptionRequest or1 = new OptionRequest();
		or1.setOptionText("option 4 edit");
		or1.setIsAnswer((byte) 0);
		Gson gson = new Gson();
		String request = gson.toJson(or1);
		mockMvc.perform(MockMvcRequestBuilders.put("/option/4")
				.content(request)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.optionText").value("option 4 edit"))
		.andDo(print());
	}

	@Test
	public void GtestGetQuiz() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/quiz/1")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(1))
		.andDo(print());
	}

	@Test
	public void HtestListQuiz() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/quiz")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(1)))
		.andDo(print());
	}

	@Test
	public void ItestDeleteOption() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/option/4")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNoContent())
		.andDo(print());
	}

	@Test
	public void JtestDeleteQuestion() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/question/1")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNoContent())
		.andDo(print());
	}
	

	@Test
	public void KtestDeleteQuiz() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/quiz/1")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNoContent())
		.andDo(print());
	}

	

	
	

	

	
}
