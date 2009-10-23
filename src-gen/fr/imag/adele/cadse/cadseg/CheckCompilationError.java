package fr.imag.adele.cadse.cadseg;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IJavaModelMarker;
import org.eclipse.jdt.core.IJavaProject;

public class CheckCompilationError {

	
	public boolean checkError(IJavaProject jp, IProgressMonitor monitor) throws CoreException {
		jp.getProject().build(IncrementalProjectBuilder.FULL_BUILD, monitor);
		jp.hasBuildState();
		
		IMarker[] markers = jp.getProject().findMarkers(IJavaModelMarker.JAVA_MODEL_PROBLEM_MARKER, true, IResource.DEPTH_INFINITE);
		if (markers == null) return false;
		
		for (IMarker iMarker : markers) {
			return true;
		}
		return false;
	}
}
