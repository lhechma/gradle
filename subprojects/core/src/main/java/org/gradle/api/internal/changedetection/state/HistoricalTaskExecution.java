/*
 * Copyright 2016 the original author or authors.
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

package org.gradle.api.internal.changedetection.state;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedMap;
import org.gradle.api.NonNullApi;
import org.gradle.caching.internal.origin.OriginMetadata;
import org.gradle.internal.fingerprint.HistoricalFileCollectionFingerprint;
import org.gradle.internal.snapshot.ValueSnapshot;
import org.gradle.internal.snapshot.impl.ImplementationSnapshot;

/**
 * State of a task when it was executed.
 */
@NonNullApi
public class HistoricalTaskExecution extends AbstractTaskExecution {

    private final boolean successful;
    private final OriginMetadata originExecutionMetadata;
    private final ImmutableSortedMap<String, HistoricalFileCollectionFingerprint> inputFingerprints;
    private final ImmutableSortedMap<String, HistoricalFileCollectionFingerprint> outputFingerprints;

    public HistoricalTaskExecution(
        ImplementationSnapshot taskImplementation,
        ImmutableList<ImplementationSnapshot> taskActionsImplementations,
        ImmutableSortedMap<String, ValueSnapshot> inputProperties,
        ImmutableSortedMap<String, HistoricalFileCollectionFingerprint> inputFingerprints,
        ImmutableSortedMap<String, HistoricalFileCollectionFingerprint> outputFingerprints,
        boolean successful,
        OriginMetadata originExecutionMetadata
    ) {
        super(taskImplementation, taskActionsImplementations, inputProperties);
        this.inputFingerprints = inputFingerprints;
        this.outputFingerprints = outputFingerprints;
        this.successful = successful;
        this.originExecutionMetadata = originExecutionMetadata;
    }

    @Override
    public boolean isSuccessful() {
        return successful;
    }

    @Override
    public OriginMetadata getOriginExecutionMetadata() {
        return originExecutionMetadata;
    }

    @Override
    public ImmutableSortedMap<String, HistoricalFileCollectionFingerprint> getInputFingerprints() {
        return inputFingerprints;
    }

    @Override
    public ImmutableSortedMap<String, HistoricalFileCollectionFingerprint> getOutputFingerprints() {
        return outputFingerprints;
    }
}
