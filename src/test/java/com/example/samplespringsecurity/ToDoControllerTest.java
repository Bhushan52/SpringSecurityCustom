package com.example.samplespringsecurity;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DemoApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ToDoControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext applicationContext;

	@Before
	public  void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();

	}

	public  void verifyAllToDoList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/todo").accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$",hasSize(4))).andDo(print());
	}
	@Test
	private void verifyToDoById() {
mockMvc.perform(MockMvcRequestBuilders.get("/todo/3").accept(MediaType.APPLICATION_JSON))
	.andExpect(jsonPath("$.id").exists())
	.andExpect(jsonPath("$.text").exists())
	.andExpect(jsonPath("$.completed").exists())
	.andExpect(jsonPath("$.id").value(3))
	.andExpect(jsonPath("$.text").value("Build the artifacts"))
	.andExpect(jsonPath("$.completed").value(false))
	.andDo(print());
	}
}
