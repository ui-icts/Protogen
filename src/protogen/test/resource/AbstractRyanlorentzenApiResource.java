package protogen.test.resource;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.ExceptionHandler;

import protogen.test.web.AbstractRyanlorentzenResource;

/**
 * Generated by Protogen
 * @since 06/30/2015 11:14:54 CDT
 */
public abstract class AbstractRyanlorentzenApiResource extends AbstractRyanlorentzenResource {

	@ExceptionHandler( value = Exception.class )
	public ResponseEntity<Map<String, Object>> handleException( Exception exception ) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put( "error", true );
		map.put( "message", exception.getMessage() );
		return new ResponseEntity<Map<String, Object>>( map, HttpStatus.INTERNAL_SERVER_ERROR );
	}

	
}