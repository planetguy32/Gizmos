package planetguy.simpleLoader;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**Marks the target as of interest to SimpleLoader. Use on classes to be checked and their constructors.
 * 
 * Constructors marked with this should take only an ID as a parameter. This is the only case in which the
 * default name won't cause trouble.
 * 
 * Methods marked with this should take no parameters and may be used to perform additional loading logic.
 * 
 * @author planetguy
 *
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface SLLoad {
	
	String name() default "unnamed"; 
	String[] dependencies() default {};
	boolean hasMetadata() default false;
	boolean isTechnical() default false; //Should exist only as dependency. TODO make it actually do so
	String itemClass() default "planetguy.simpleLoader.SLItemBlock";
	

}
