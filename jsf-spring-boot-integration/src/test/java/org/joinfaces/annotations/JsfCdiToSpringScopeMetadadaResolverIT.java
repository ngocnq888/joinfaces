/*
 * Copyright 2016-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.joinfaces.annotations;

import org.junit.Test;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationScopeMetadataResolver;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;

import static org.assertj.core.api.Assertions.assertThat;

public class JsfCdiToSpringScopeMetadadaResolverIT {

	@Test
	public void testViewScope() {
		AnnotationMetadata annotationMetadata = new StandardAnnotationMetadata(ViewScopedClass.class);
		AnnotatedBeanDefinition beanDefinition = new AnnotatedGenericBeanDefinition(annotationMetadata);
		AnnotationScopeMetadataResolver annotationScopeMetadataResolver = new JsfCdiToSpringScopeMetadataResolver();

		ScopeMetadata scopeMetadata = annotationScopeMetadataResolver.resolveScopeMetadata(
			beanDefinition);

		assertThat(scopeMetadata.getScopeName()).isEqualTo(JsfCdiToSpring.VIEW);
	}

	@Test
	public void testSessionScope() {
		AnnotationMetadata annotationMetadata = new StandardAnnotationMetadata(SessionScopedClass.class);
		AnnotatedBeanDefinition beanDefinition = new AnnotatedGenericBeanDefinition(annotationMetadata);
		AnnotationScopeMetadataResolver annotationScopeMetadataResolver = new JsfCdiToSpringScopeMetadataResolver();

		ScopeMetadata scopeMetadata = annotationScopeMetadataResolver.resolveScopeMetadata(
			beanDefinition);

		assertThat(scopeMetadata.getScopeName()).isEqualTo(JsfCdiToSpring.SESSION);
	}

	@Test
	public void testNoScope() {
		AnnotationMetadata annotationMetadata = new StandardAnnotationMetadata(NoScopedClass.class);
		AnnotatedBeanDefinition beanDefinition = new AnnotatedGenericBeanDefinition(annotationMetadata);
		AnnotationScopeMetadataResolver annotationScopeMetadataResolver = new JsfCdiToSpringScopeMetadataResolver();

		ScopeMetadata scopeMetadata = annotationScopeMetadataResolver.resolveScopeMetadata(
			beanDefinition);

		assertThat(scopeMetadata.getScopeName()).isEqualTo(JsfCdiToSpring.SINGLETON);
	}

	@Test
	public void testGenericBeanDefinition() {
		AnnotationMetadata annotationMetadata = new StandardAnnotationMetadata(SessionScopedClass.class);
		AnnotatedBeanDefinition annotatedBeanDefinition = new AnnotatedGenericBeanDefinition(annotationMetadata);
		GenericBeanDefinition beanDefinition = new GenericBeanDefinition(annotatedBeanDefinition);
		AnnotationScopeMetadataResolver annotationScopeMetadataResolver = new JsfCdiToSpringScopeMetadataResolver();

		ScopeMetadata scopeMetadata = annotationScopeMetadataResolver.resolveScopeMetadata(
			beanDefinition);

		assertThat(scopeMetadata.getScopeName()).isEqualTo(JsfCdiToSpring.SINGLETON);
	}

}
