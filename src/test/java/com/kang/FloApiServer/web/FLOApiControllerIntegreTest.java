package com.kang.FloApiServer.web;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kang.FloApiServer.domain.playsong.PlaySong;
import com.kang.FloApiServer.domain.playsong.PlaySongRepository;
import com.kang.FloApiServer.domain.song.CategoryType;
import com.kang.FloApiServer.domain.song.Song;
import com.kang.FloApiServer.domain.song.SongRepository;
import com.kang.FloApiServer.domain.storage.Storage;
import com.kang.FloApiServer.domain.storage.StorageRepository;
import com.kang.FloApiServer.domain.user.RoleType;
import com.kang.FloApiServer.domain.user.User;
import com.kang.FloApiServer.web.dto.playsong.PlaySongSaveReqDto;
import com.kang.FloApiServer.web.dto.storage.StorageSaveDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@AutoConfigureMockMvc
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost", uriPort = 8080)
@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class FLOApiControllerIntegreTest {

	protected RestDocumentationResultHandler document;
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private SongRepository songRepository;

	@Autowired
	private PlaySongRepository playSongRepository;
	
//	@Autowired  //시큐리티 세션을 어떻게 처리할지 모르겠음.
//	private UserRepository userRepository;
	
	@Autowired
	private StorageRepository storageRepository;

	
	@Autowired
	private EntityManager entityManager;

	protected MockHttpSession session;
	protected MockHttpServletRequest request;
	
	
	@BeforeEach
	private void setup(WebApplicationContext webApplicationContext,
			RestDocumentationContextProvider restDocumentation) {
		
		this.document = document("{class-name}/{method-name}",
				Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
				Preprocessors.preprocessResponse(Preprocessors.prettyPrint()));

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.addFilter(new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true))
				.apply(documentationConfiguration(restDocumentation)).alwaysDo(document).build();
		
		
		entityManager.createNativeQuery("ALTER TABLE storage AUTO_INCREMENT =1").executeUpdate();
		entityManager.createNativeQuery("ALTER TABLE song AUTO_INCREMENT =1").executeUpdate();
		entityManager.createNativeQuery("ALTER TABLE playSong AUTO_INCREMENT =1").executeUpdate();
			
		User user = new User(1, "", "", "", RoleType.USER, null); 
		session = new MockHttpSession();
		session.setAttribute("principal", user);
	}
	

	@Test
	public void playSongSave_테스트() throws Exception {
		//given(테스트를 하기 위한 준비)
		PlaySongSaveReqDto playSongSaveReqDto = new PlaySongSaveReqDto();
		Song song = new Song(1, "있잖아 (ROCK VER.)", "IU (아이유)", CategoryType.DANCE, "lyrics", "2009.04", "IU (아이유) - 있잖아.jpg", "IU (아이유) - 있잖아 (ROCK VER.).mp3");						
		User user = new User(1, "", "", "", RoleType.USER, null);
		
		playSongSaveReqDto.setSong(song);
		playSongSaveReqDto.setUser(user);
		
		String content = new ObjectMapper().writeValueAsString(playSongSaveReqDto);

	  //when(테스트 실행)
		ResultActions resultAction = mockMvc.perform(post("/playSong")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content)
				.accept(MediaType.APPLICATION_JSON_UTF8));
		
		//then(검증)
		resultAction
		.andExpect(jsonPath("$.statusCode").value(1))
		.andDo(MockMvcResultHandlers.print())
		.andDo(document); //API 문서 만들기;
	}

	@Test
	public void findPlaySongByUserId_테스트() throws Exception {
		int id =1;
		List<PlaySong> playSongs = new ArrayList<>();

		Song song = new Song(1, "있잖아 (ROCK VER.)", "IU (아이유)", CategoryType.DANCE, "lyrics", "2009.04", "IU (아이유) - 있잖아.jpg", "IU (아이유) - 있잖아 (ROCK VER.).mp3");
		Song song2 = new Song(2, "마지막처럼", "BLACKPINK", CategoryType.DANCE, "", "2017.06", "BLACKPINK - 마지막처럼.jpg", "BLACKPINK - 마지막처럼.mp3");
		Song song3 = new Song(3, "Into the I-LAND", "IU (아이유)", CategoryType.DANCE, "", "2020.06", "IU (아이유) - Into the I-LAND.jpg", "IU (아이유) - Into the I-LAND.mp3");
		User user = new User(1, "", "", "", RoleType.USER, null);
		playSongs.add(new PlaySong(1, user, song , null));
		playSongs.add(new PlaySong(2, user, song2 , null));
		playSongs.add(new PlaySong(3, user, song3 , null));
		playSongRepository.saveAll(playSongs);

		ResultActions resultActions = mockMvc.perform(get("/playSong/{id}",id)
				.accept(MediaType.APPLICATION_JSON_UTF8));
			
		resultActions
		.andExpect(jsonPath("$.statusCode").value(1))
		.andDo(MockMvcResultHandlers.print())
		.andDo(document); //API 문서 만들기


	}
	
	
	@Test
	public void deletePlaySongById_테스트() throws Exception{
		int id = 2;  //왜 1은 안 될까 1도 넣어줬는데...
		List<PlaySong> playSongs = new ArrayList<>();

		Song song = new Song(1, "있잖아 (ROCK VER.)", "IU (아이유)", CategoryType.DANCE, "lyrics", "2009.04", "IU (아이유) - 있잖아.jpg", "IU (아이유) - 있잖아 (ROCK VER.).mp3");
		Song song2 = new Song(2, "마지막처럼", "BLACKPINK", CategoryType.DANCE, "", "2017.06", "BLACKPINK - 마지막처럼.jpg", "BLACKPINK - 마지막처럼.mp3");
		Song song3 = new Song(3, "Into the I-LAND", "IU (아이유)", CategoryType.DANCE, "", "2020.06", "IU (아이유) - Into the I-LAND.jpg", "IU (아이유) - Into the I-LAND.mp3");
		User user = new User(1, "", "", "", RoleType.USER, null);
		playSongs.add(new PlaySong(1, user, song , null));
		playSongs.add(new PlaySong(2, user, song2 , null));
		playSongs.add(new PlaySong(3, user, song3 , null));
		playSongRepository.saveAll(playSongs);
		
		ResultActions resultAction = mockMvc.perform(delete("/playSong/{id}", id)
				.accept(MediaType.APPLICATION_JSON_UTF8));
		
		resultAction
		.andExpect(jsonPath("$.statusCode").value(1))
		.andDo(MockMvcResultHandlers.print())
		.andDo(document);

		
	}
	
	
	@Test
	public void findCategorySong_테스트() throws Exception{
		//given
		List<Song> songs = new ArrayList<>();
		Song song = new Song(1, "있잖아 (ROCK VER.)1111", "IU (아이유)", CategoryType.POP, "", "", "", "");
		Song song2 = new Song(2, "마지막처럼1111", "BLACKPINK1111", CategoryType.POP, "", "", "", "");
		Song song3 = new Song(3, "Into the I-LAND1111", "IU (아이유)1111", CategoryType.POP, "", "", "", "");
		songs.add(song);
		songs.add(song2);
		songs.add(song3);
		songRepository.saveAll(songs);
		
	
		ResultActions resultActions = mockMvc.perform(get("/song/category?category=POP")//.param("category", CategoryType.DANCE.toString())
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)); //이유를 모르겠다..
		
		resultActions
		.andExpect(jsonPath("$.statusCode").value(1))
		.andDo(MockMvcResultHandlers.print())
		.andDo(document); //API 문서 만들기

	}
	
	
	
	@Test
	public void searchSongList_테스트() throws Exception{
		//given
		List<Song> songs = new ArrayList<>();
		Song song = new Song(1, "있잖아 (ROCK VER.)22", "IU (아이유)", CategoryType.POP, "", "", "", "");
		Song song2 = new Song(2, "마지막처럼22", "BLACKPINK", CategoryType.POP, "", "", "", "");
		Song song3 = new Song(3, "Into the I-LAND22", "IU (아이유)", CategoryType.POP, "", "", "", "");
		songs.add(song);
		songs.add(song2);
		songs.add(song3);
		songRepository.saveAll(songs);
		
	
		ResultActions resultActions = mockMvc.perform(get("/song/search").param("keyword", "있잖아")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED).accept(MediaType.APPLICATION_JSON_UTF8_VALUE));
		
		resultActions
		.andExpect(jsonPath("$.statusCode").value(1))
		.andDo(MockMvcResultHandlers.print())
		.andDo(document); //API 문서 만들기

	}
	
	
	@Test
	public void findSongList_테스트() throws Exception{
		//given
		List<Song> songs = new ArrayList<>();
		Song song = new Song(1, "있잖아 (ROCK VER.)22", "IU (아이유)", CategoryType.POP, "", "", "", "");
		Song song2 = new Song(2, "마지막처럼22", "BLACKPINK", CategoryType.POP, "", "", "", "");
		Song song3 = new Song(3, "Into the I-LAND22", "IU (아이유)", CategoryType.POP, "", "", "", "");
		songs.add(song);
		songs.add(song2);
		songs.add(song3);
		songRepository.saveAll(songs);
		
	
		ResultActions resultActions = mockMvc.perform(get("/song")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED).accept(MediaType.APPLICATION_JSON_UTF8_VALUE));
		
		resultActions
		.andExpect(jsonPath("$.statusCode").value(1))
		.andDo(MockMvcResultHandlers.print())
		.andDo(document); //API 문서 만들기

	}
	

	@Test
	public void findStorageList_테스트() throws Exception{
		//given
		List<Storage> storages = new ArrayList<>();
		//Storage storage = new Storage();
		Storage storage = new Storage(1, null, null, null, null);
		storages.add(storage);
		storageRepository.saveAll(storages);
		
		ResultActions resultActions = mockMvc.perform(get("/storage")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED).accept(MediaType.APPLICATION_JSON_UTF8_VALUE));
		
		resultActions
		.andExpect(jsonPath("$.statusCode").value(1))
		.andDo(MockMvcResultHandlers.print())
		.andDo(document); //API 문서 만들기

	}
	
	
	@Test
	public void saveStorage_테스트() throws Exception{
		//given
		StorageSaveDto storageSaveDto =new StorageSaveDto("1");	
		String content = new ObjectMapper().writeValueAsString(storageSaveDto);

	  //when(테스트 실행)
		ResultActions resultAction = mockMvc.perform(post("/storage")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content)
				.accept(MediaType.APPLICATION_JSON_UTF8));
		 
		resultAction
		.andExpect(jsonPath("$.statusCode").value(1))
		.andDo(MockMvcResultHandlers.print())
		.andDo(document); //API 문서 만들기

	}
	
	
	@Test
	public void deleteStorageById_테스트() throws Exception{
		//given
		int id = 1;  //왜 안되지...?
		storageRepository.save(new Storage(1, null, null, null, null));
		storageRepository.save(new Storage(2, null, null, null, null));
		
		log.info("뭔데~~~"+storageRepository.findById(19).toString()+"   아아아아아");
		
		ResultActions resultActions = mockMvc.perform(delete("/storage/{id}", id)
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE));
		
		resultActions
		.andExpect(jsonPath("$.statusCode").value(1))
		.andDo(MockMvcResultHandlers.print())
		.andDo(document); //API 문서 만들기

	}
	
	
	
	
	


//	@Test
//	public void userUpdate_테스트() throws Exception {  //계속 시큐리티 login으로 리다이렉션되네..
//		
//		int id =1;
//		
//		AuthJoinReqDto authJoinReqDto = new AuthJoinReqDto();
//		authJoinReqDto.setUsername("ssar");
//		authJoinReqDto.setPassword("1234");
//		authJoinReqDto.setEmail("ssar@nate.com");
//
//		String content = new ObjectMapper().writeValueAsString(authJoinReqDto);
//
//		ResultActions resultAction = mockMvc.perform(put("/user/{id}", id).contentType(MediaType.APPLICATION_JSON_UTF8)
//				.session(session) //가짜 세션을 사용
//				.content(content).accept(MediaType.APPLICATION_JSON_UTF8));
//
//		resultAction.andExpect(jsonPath("$.statusCode").value(1)).andDo(MockMvcResultHandlers.print());
//
//	}
	
	
	
	
	
	
	
	

}
