package jumpstart.web.services;

import java.util.Map;

import jumpstart.web.translators.MoneyTranslator;
import jumpstart.web.translators.YesNoTranslator;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.Translator;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.commons.Configuration;
import org.apache.tapestry5.commons.MappedConfiguration;
import org.apache.tapestry5.commons.OrderedConfiguration;
import org.apache.tapestry5.internal.beanvalidator.BaseCCD;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.ioc.services.ClasspathURLConverter;
import org.apache.tapestry5.ioc.services.ServiceOverride;
import org.apache.tapestry5.ioc.services.ThreadLocale;
import org.apache.tapestry5.services.BeanBlockContribution;
import org.apache.tapestry5.services.ComponentRequestFilter;
import org.apache.tapestry5.services.DisplayBlockContribution;
import org.apache.tapestry5.services.EditBlockContribution;
import org.apache.tapestry5.services.javascript.DataConstants;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.apache.tapestry5.services.security.WhitelistAnalyzer;
import org.apache.tapestry5.services.transform.ComponentClassTransformWorker2;
import org.apache.tapestry5.beanvalidator.ClientConstraintDescriptor;
import org.slf4j.Logger;

/**
 * This module is automatically included as part of the Tapestry IoC Registry, it's a good place to configure and extend
 * Tapestry, or to place your own service definitions. See http://tapestry.apache.org/5.3.4/tapestry-ioc/module.html
 */
public class AppModule {
    private static final String UPLOADS_PATH = "jumpstart.upload-path";

    @Inject
    @Symbol(SymbolConstants.PRODUCTION_MODE)
    @Property(write = false)
    private static boolean productionMode;


    // Tell Tapestry about our custom translators and their message file.
    // We do this by contributing configuration to Tapestry's TranslatorAlternatesSource service, FieldValidatorSource
    // service, and ComponentMessagesSource service.

    @SuppressWarnings("rawtypes")
    public static void contributeTranslatorAlternatesSource(MappedConfiguration<String, Translator> configuration,
                                                            ThreadLocale threadLocale) {
        configuration.add("yesno", new YesNoTranslator("yesno"));
        configuration.add("money2", new MoneyTranslator("money2", 2, threadLocale));
    }

    public void contributeComponentMessagesSource(OrderedConfiguration<String> configuration) {
        configuration.add("myTranslationMessages", "jumpstart/web/translators/TranslationMessages");
    }

    public static void contributeApplicationDefaults(MappedConfiguration<String, String> configuration) {
        configuration.add(SymbolConstants.SUPPORTED_LOCALES, "en_US,en_GB,fr");
        configuration.add(SymbolConstants.JAVASCRIPT_INFRASTRUCTURE_PROVIDER, "jquery");
    }

    // Tell Tapestry how to detect and protect pages that require security.
    // We do this by contributing a custom ComponentRequestFilter to Tapestry's ComponentRequestHandler service.
    // - ComponentRequestHandler is shown in
    // http://tapestry.apache.org/request-processing.html#RequestProcessing-Overview
    // - Based on http://tapestryjava.blogspot.com/2009/12/securing-tapestry-pages-with.html

    /*public void contributeComponentRequestHandler(OrderedConfiguration<ComponentRequestFilter> configuration) {
        configuration.addInstance("PageProtectionFilter", PageProtectionFilter.class);
    }*/
}