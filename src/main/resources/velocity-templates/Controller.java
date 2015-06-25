package ${packageName};

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.NonUniqueObjectException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.uiowa.icts.datatable.DataTable;
import edu.uiowa.icts.datatable.DataTableColumn;
import edu.uiowa.icts.datatable.DataTableRequest;
import ${domainPackageName}.*;
import edu.uiowa.icts.spring.GenericDaoListOptions;

/**
 * Generated by Protogen 
 * @since ${date}
 */
@Controller( value = "${controllerName}" )
@RequestMapping( "${pathPrefix}" )
public class ${className} extends ${abstractControllerClassName} {

    private static final Log log = LogFactory.getLog( ${className}.class );

    @RequestMapping( value = "list_alt${pathExtension}", method = RequestMethod.GET )
    public String listNoScript(Model model) {
        model.addAttribute( "${lowerDomainName}List", ${daoServiceName}.get${domainName}Service().list() );
        return "${jspPath}/list_alt";
    }

    @RequestMapping( value = { "list${pathExtension}", "", "/" }, method = RequestMethod.GET )
    public String list() {
        return "${jspPath}/list";
    }

${datatableMethod}

    @RequestMapping( value = "add${pathExtension}", method = RequestMethod.GET )
    public String add( Model model ) {
        model.addAttribute( "${lowerDomainName}", new ${domainName}() );
${addEditListDependencies}
        return "${jspPath}/edit";
    }

    @RequestMapping( value = "edit${pathExtension}", method = RequestMethod.GET )
    public String edit( ModelMap model, ${requestParameterIdentifier} ) {
${addEditListDependencies}
${compositeKey}
        model.addAttribute( "${lowerDomainName}", ${daoServiceName}.get${domainName}Service().findById( ${lowerDomainName}Id ) );
        return "${jspPath}/edit";
    }

    @RequestMapping( value = "show${pathExtension}", method = RequestMethod.GET )
    public String show( ModelMap model, ${requestParameterIdentifier} ) {
${compositeKey}
        model.addAttribute( "${lowerDomainName}", ${daoServiceName}.get${domainName}Service().findById( ${lowerDomainName}Id ) );
        return "${jspPath}/show";
    }

    @RequestMapping( value = "save${pathExtension}", method = RequestMethod.POST )
    public String save(@Valid @ModelAttribute( "${lowerDomainName}" ) ${domainName} ${lowerDomainName}, BindingResult result, Model model ) {
${newCompositeKey}
${compositeKeySetter}
		if (result.hasErrors()) { 
			${addEditListDependencies}
			return "${jspPath}/edit"; 
		} else {
			try {
				${daoServiceName}.get${domainName}Service().saveOrUpdate( ${lowerDomainName} );
			} catch (NonUniqueObjectException e) {
				log.debug("Merging Results");
				${daoServiceName}.get${domainName}Service().merge( ${lowerDomainName} );
			}
		}
		return "redirect:${pathPrefix}/list${pathExtension}";
    }

    @RequestMapping( value = "delete${pathExtension}", method = RequestMethod.GET )
    public String confirmDelete( ModelMap model, ${requestParameterIdentifier} ) {
${compositeKey}
        model.addAttribute( "${lowerDomainName}", ${daoServiceName}.get${domainName}Service().findById( ${lowerDomainName}Id ) );
        return "${jspPath}/delete";
    }

    @RequestMapping( value = "delete${pathExtension}", method = RequestMethod.POST )
    public String doDelete( ModelMap model, @RequestParam( value = "submit" ) String submitButtonValue, ${requestParameterIdentifier} ) {
${compositeKey}
        if ( StringUtils.equalsIgnoreCase( submitButtonValue, "yes" ) ) {
            ${daoServiceName}.get${domainName}Service().delete( ${daoServiceName}.get${domainName}Service().findById( ${lowerDomainName}Id ) );
        }
        return "redirect:${pathPrefix}/list${pathExtension}";
    }
}