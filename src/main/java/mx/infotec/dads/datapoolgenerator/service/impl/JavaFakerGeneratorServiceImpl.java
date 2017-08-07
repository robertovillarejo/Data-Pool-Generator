package mx.infotec.dads.datapoolgenerator.service.impl;

import mx.infotec.dads.datapoolgenerator.domain.DataType;
import mx.infotec.dads.datapoolgenerator.service.JavaFakerGeneratorService;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

@Service
public class JavaFakerGeneratorServiceImpl implements JavaFakerGeneratorService {

	private final Logger log = LoggerFactory.getLogger(JavaFakerGeneratorServiceImpl.class);

	Faker faker;

	public JavaFakerGeneratorServiceImpl() {
		faker = new Faker();
	}

	@Override
	public String generate(DataType dataType) {

		String data = null;
		switch (dataType) {
		case NAME:
			data = faker.name().firstName();
			break;

		case LAST_NAME:
			data = faker.name().lastName();
			break;

		case EMAIL:
			data = faker.internet().emailAddress();
			break;

		case LOREM:
			data = faker.lorem().characters();
			break;

		case COMPANY:
			data = faker.company().name();
			break;

		default:
			break;
		}
		return data;
	
	}

	@Override
	public List<String> generate(DataType dataType, int n) {

		List<String> data = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			data.add(generate(dataType));
		}
		return data;
	}
}
