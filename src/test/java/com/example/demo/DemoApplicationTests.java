package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class DemoApplicationTests {
	Calc calc= new Calc();

	@Test
	void addNumbers() {
		//given
		int a=20;
		int b=30;

		//when
		int resultset =calc.add( a, b);
		// then
		int expected=50;
		assertThat(resultset).isEqualTo(expected);
	}

	class Calc {
		int add (int a, int b){
			return a+b;
		}

	}

}
