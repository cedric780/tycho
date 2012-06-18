/*******************************************************************************
 * Copyright (c) 2008, 2012 Sonatype Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Sonatype Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.tycho.p2.resolver.facade;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.eclipse.tycho.ArtifactKey;
import org.eclipse.tycho.artifacts.TargetPlatform;
import org.eclipse.tycho.artifacts.TargetPlatformFilter;
import org.eclipse.tycho.p2.metadata.IReactorArtifactFacade;
import org.eclipse.tycho.p2.target.facade.TargetPlatformBuilder;

public interface P2Resolver {
    /**
     * Pseudo artifact type used to denote P2 installable unit dependencies
     * 
     * @see ArtifactKey
     */
    public static final String TYPE_INSTALLABLE_UNIT = "p2-installable-unit";

    public static final String ANY_QUALIFIER = "qualifier";

    public void setEnvironments(List<Map<String, String>> properties);

    /**
     * Additional artifact available for resolution.
     */
    public void addReactorArtifact(IReactorArtifactFacade artifact);

    /**
     * Additional filter to be applied on the list of available units before resolution.
     */
    public void addFilters(List<TargetPlatformFilter> filters);

    /**
     * Additional requirement to be resolved.
     */
    public void addDependency(String type, String id, String versionRange);

    /**
     * Returns list ordered of resolution result, one per requested TargetEnvironment.
     * 
     * @TODO this should return Map<TargetEnvironment,P2ResolutionResult>
     */
    public List<P2ResolutionResult> resolveProject(TargetPlatform context, File location);

    public P2ResolutionResult collectProjectDependencies(TargetPlatform context, File projectLocation);

    public P2ResolutionResult resolveMetadata(TargetPlatformBuilder context, Map<String, String> properties);

    /**
     * Resolves specified installable unit identified by id and version.
     */
    public P2ResolutionResult resolveInstallableUnit(TargetPlatform context, String id, String version);

}
