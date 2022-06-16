/*
 * Copyright 2016-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.joinfaces.mojarra;

import java.net.URI;
import java.util.Collection;

import jakarta.servlet.ServletContext;

import com.sun.faces.spi.ConfigurationResourceProvider;
import com.sun.faces.spi.FacesConfigResourceProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class SpringFaceletConfigResourceProviderTest {

	@Autowired
	private ServletContext servletContext;

	private ConfigurationResourceProvider provider;

	@BeforeEach
	void setUp() {
		this.provider = new SpringFaceletConfigResourceProvider();
	}

	@Test
	void test() {
		Collection<URI> resources = this.provider.getResources(servletContext);

		assertThat(resources).isNotEmpty();
	}
}
