package upm.oeg.wsld.jena;

import java.io.InputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.rdf.model.Resource;

/**
 * Task 07: Querying ontologies (RDFs)
 * @author elozano
 * @author isantana
 *
 */
public class Task07
{
	public static String ns = "http://somewhere#";
	
	public static void main(String args[])
	{
		String filename = "resources/example6.rdf";
		
		// Create an empty model
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
		
		// Use the FileManager to find the input file
		InputStream in = FileManager.get().open(filename);
	
		if (in == null)
			throw new IllegalArgumentException("File: "+filename+" not found");
	
		// Read the RDF/XML file
		model.read(in, null);
		
		
		// ** TASK 7.1: List all individuals of "Person" **
		OntClass person = model.getOntClass(ns+"Person");
		ExtendedIterator instances = person.listInstances();
		
		System.out.println("\nTASK 7.1: List of all individuals");
		while (instances.hasNext()){
			Individual var1=(Individual) instances.next();
			System.out.println("Person: " + var1.getURI());
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator subclasses = person.listSubClasses();
		
		System.out.println("\nTASK 7.2: List of subclasses of Person");
		while (subclasses.hasNext()){
			OntClass var2=(OntClass) subclasses.next();
			System.out.println("\nSubclass of Person: "+var2.getURI());
		}
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		System.out.println("\nTASK 7.3: List instances and subclasses");
		OntModel varModel=ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model);
		OntClass varPerson=varModel.getOntClass(ns+"Person");
		ExtendedIterator varSubclasses=varPerson.listSubClasses();
		ExtendedIterator varInstances=varPerson.listInstances();
		
		while (varSubclasses.hasNext()) {
			OntClass subclss=(OntClass) varSubclasses.next();
			System.out.println("\tSubclase de persona: " + subclss.getURI());
		}
		
		while (varInstances.hasNext()) {
			Individual instnc=(Individual) varInstances.next();
			System.out.println("\tInstance of Person: " + instnc.getURI());
		}
	
	}
}
