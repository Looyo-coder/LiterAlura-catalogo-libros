package com.literalura;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = LiterAluraCatalogoLibrosApplication.class)
class ContextSanityCheckTest {

	@Test
	void sanityCheck() {
		// Si este test pasa, el contexto básico está bien.
	}
}
