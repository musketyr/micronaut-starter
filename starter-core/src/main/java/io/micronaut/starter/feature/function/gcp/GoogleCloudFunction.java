/*
 * Copyright 2017-2020 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.starter.feature.function.gcp;

import com.fizzed.rocker.RockerModel;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.starter.application.Project;
import io.micronaut.starter.feature.function.gcp.template.gcpFunctionGroovyJunit;
import io.micronaut.starter.feature.function.gcp.template.gcpFunctionJavaJunit;
import io.micronaut.starter.feature.function.gcp.template.gcpFunctionKoTest;
import io.micronaut.starter.feature.function.gcp.template.gcpFunctionKotlinJunit;
import io.micronaut.starter.feature.function.gcp.template.gcpFunctionSpock;
import io.micronaut.starter.feature.other.ShadePlugin;
import io.micronaut.starter.options.BuildTool;
import jakarta.inject.Singleton;

/**
 * A feature for supporting Google Cloud Function.
 *
 * @author graemerocher
 * @since 2.0.0
 */
@Singleton
public class GoogleCloudFunction extends AbstractGoogleCloudFunction {

    public static final String NAME = "google-cloud-function-http";

    public GoogleCloudFunction(ShadePlugin shadePlugin) {
        super(shadePlugin);
    }

    @NonNull
    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getTitle() {
        return "Google Cloud Function";
    }

    @Override
    public String getDescription() {
        return "Adds support for writing functions to deploy to Google Cloud Function";
    }

    @Override
    public boolean isVisible() {
        return false;
    }

    @Override
    public RockerModel javaJUnitTemplate(Project project) {
        return gcpFunctionJavaJunit.template(project);
    }

    @Override
    public RockerModel kotlinJUnitTemplate(Project project) {
        return gcpFunctionKotlinJunit.template(project);
    }

    @Override
    public RockerModel groovyJUnitTemplate(Project project) {
        return gcpFunctionGroovyJunit.template(project);
    }

    @Override
    protected RockerModel koTestTemplate(Project project) {
        return gcpFunctionKoTest.template(project);
    }

    @Override
    public RockerModel spockTemplate(Project project) {
        return gcpFunctionSpock.template(project);
    }

    @Override
    protected String getRunCommand(BuildTool buildTool) {
        if (buildTool == BuildTool.MAVEN) {
            return "mvnw function:run";
        } else {
            return "gradlew runFunction";
        }
    }

    @Override
    protected String getBuildCommand(BuildTool buildTool) {
        if (buildTool == BuildTool.MAVEN) {
            return "mvnw clean package";
        } else {
            return "gradlew clean shadowJar";
        }
    }

    @Override
    public String getMicronautDocumentation() {
        return "https://micronaut-projects.github.io/micronaut-gcp/latest/guide/index.html#httpFunctions";
    }

}
