package planetguy.simpleLoader;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**Marks a field as to be filled from the config by SimpleLoader. SimpleLoader handles boilerplate like
 * block and item IDs, so don't use this for them. Use on public static ints, doubles, strings or string
 * arrays. The default config value will be whatever you initialize the field to.
 * 
 * @author planetguy
 *
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SLProp {
	
	String name();
	String category() default "Details";
	

}
