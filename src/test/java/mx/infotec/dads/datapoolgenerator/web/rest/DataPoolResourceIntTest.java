package mx.infotec.dads.datapoolgenerator.web.rest;

import mx.infotec.dads.datapoolgenerator.DataPoolGeneratorApp;

import mx.infotec.dads.datapoolgenerator.domain.DataPool;
import mx.infotec.dads.datapoolgenerator.repository.DataPoolRepository;
import mx.infotec.dads.datapoolgenerator.service.DataPoolGeneratorService;
import mx.infotec.dads.datapoolgenerator.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the DataPoolResource REST controller.
 *
 * @see DataPoolResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataPoolGeneratorApp.class)
public class DataPoolResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private DataPoolRepository dataPoolRepository;
    
    @Autowired
    private DataPoolGeneratorService dataPoolGenerator;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restDataPoolMockMvc;

    private DataPool dataPool;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DataPoolResource dataPoolResource = new DataPoolResource(dataPoolRepository, dataPoolGenerator);
        this.restDataPoolMockMvc = MockMvcBuilders.standaloneSetup(dataPoolResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DataPool createEntity() {
        DataPool dataPool = new DataPool()
            .name(DEFAULT_NAME);
        return dataPool;
    }

    @Before
    public void initTest() {
        dataPoolRepository.deleteAll();
        dataPool = createEntity();
    }

    @Test
    public void createDataPool() throws Exception {
        int databaseSizeBeforeCreate = dataPoolRepository.findAll().size();

        // Create the DataPool
        restDataPoolMockMvc.perform(post("/api/data-pools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dataPool)))
            .andExpect(status().isCreated());

        // Validate the DataPool in the database
        List<DataPool> dataPoolList = dataPoolRepository.findAll();
        assertThat(dataPoolList).hasSize(databaseSizeBeforeCreate + 1);
        DataPool testDataPool = dataPoolList.get(dataPoolList.size() - 1);
        assertThat(testDataPool.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    public void createDataPoolWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dataPoolRepository.findAll().size();

        // Create the DataPool with an existing ID
        dataPool.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restDataPoolMockMvc.perform(post("/api/data-pools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dataPool)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<DataPool> dataPoolList = dataPoolRepository.findAll();
        assertThat(dataPoolList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllDataPools() throws Exception {
        // Initialize the database
        dataPoolRepository.save(dataPool);

        // Get all the dataPoolList
        restDataPoolMockMvc.perform(get("/api/data-pools?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dataPool.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }

    @Test
    public void getDataPool() throws Exception {
        // Initialize the database
        dataPoolRepository.save(dataPool);

        // Get the dataPool
        restDataPoolMockMvc.perform(get("/api/data-pools/{id}", dataPool.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dataPool.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    public void getNonExistingDataPool() throws Exception {
        // Get the dataPool
        restDataPoolMockMvc.perform(get("/api/data-pools/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateDataPool() throws Exception {
        // Initialize the database
        dataPoolRepository.save(dataPool);
        int databaseSizeBeforeUpdate = dataPoolRepository.findAll().size();

        // Update the dataPool
        DataPool updatedDataPool = dataPoolRepository.findOne(dataPool.getId());
        updatedDataPool
            .name(UPDATED_NAME);

        restDataPoolMockMvc.perform(put("/api/data-pools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDataPool)))
            .andExpect(status().isOk());

        // Validate the DataPool in the database
        List<DataPool> dataPoolList = dataPoolRepository.findAll();
        assertThat(dataPoolList).hasSize(databaseSizeBeforeUpdate);
        DataPool testDataPool = dataPoolList.get(dataPoolList.size() - 1);
        assertThat(testDataPool.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    public void updateNonExistingDataPool() throws Exception {
        int databaseSizeBeforeUpdate = dataPoolRepository.findAll().size();

        // Create the DataPool

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDataPoolMockMvc.perform(put("/api/data-pools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dataPool)))
            .andExpect(status().isCreated());

        // Validate the DataPool in the database
        List<DataPool> dataPoolList = dataPoolRepository.findAll();
        assertThat(dataPoolList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteDataPool() throws Exception {
        // Initialize the database
        dataPoolRepository.save(dataPool);
        int databaseSizeBeforeDelete = dataPoolRepository.findAll().size();

        // Get the dataPool
        restDataPoolMockMvc.perform(delete("/api/data-pools/{id}", dataPool.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DataPool> dataPoolList = dataPoolRepository.findAll();
        assertThat(dataPoolList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DataPool.class);
        DataPool dataPool1 = new DataPool();
        dataPool1.setId("id1");
        DataPool dataPool2 = new DataPool();
        dataPool2.setId(dataPool1.getId());
        assertThat(dataPool1).isEqualTo(dataPool2);
        dataPool2.setId("id2");
        assertThat(dataPool1).isNotEqualTo(dataPool2);
        dataPool1.setId(null);
        assertThat(dataPool1).isNotEqualTo(dataPool2);
    }
}
