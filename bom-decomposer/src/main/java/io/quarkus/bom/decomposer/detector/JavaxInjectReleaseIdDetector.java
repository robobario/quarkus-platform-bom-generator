package io.quarkus.bom.decomposer.detector;

import io.quarkus.bom.decomposer.BomDecomposerException;
import io.quarkus.bom.decomposer.ReleaseIdDetector;
import io.quarkus.domino.scm.ScmRepository;
import io.quarkus.domino.scm.ScmRevision;
import io.quarkus.domino.scm.ScmRevisionResolver;
import org.eclipse.aether.artifact.Artifact;

public class JavaxInjectReleaseIdDetector implements ReleaseIdDetector {

    @Override
    public ScmRevision detectReleaseId(ScmRevisionResolver releaseResolver, Artifact artifact)
            throws BomDecomposerException {
        if (artifact.getGroupId().equals("javax.inject")) {
            var releaseId = releaseResolver.readRevisionFromPom(artifact);
            if (releaseId.getRepository().hasUrl()
                    && releaseId.getRepository().getUrl().equals("https://github.com/javax-inject/javax-inject")) {
                return releaseId;
            }
            return ScmRevision.tag(ScmRepository.ofUrl("https://github.com/javax-inject/javax-inject"),
                    releaseId.getValue());
        }
        return null;
    }

}
