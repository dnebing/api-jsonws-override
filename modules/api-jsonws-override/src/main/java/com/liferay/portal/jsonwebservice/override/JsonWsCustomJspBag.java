package com.liferay.portal.jsonwebservice.override;

import com.liferay.portal.custom.jsp.bag.BaseCustomJspBag;
import com.liferay.portal.deploy.hot.CustomJspBag;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import java.net.URL;
import java.util.Enumeration;

/**
 * class JsonWsCustomJspBag: This is the custom jsp bag used to replace the core JSP files for the jsonws UI.
 *
 * @author dnebinger
 */
@Component(
	immediate = true,
	property = {
		"context.id=JsonWsCustomJspBag",
		"context.name=/api/jsonws Permissioning Custom JSP Bag",
		"service.ranking:Integer=20"
	}
)
public class JsonWsCustomJspBag extends BaseCustomJspBag implements CustomJspBag {

	@Activate
	protected void activate(BundleContext bundleContext) {
		super.activate(bundleContext);

		// we also want to include the jspf files in the list
		Enumeration<URL> enumeration = bundleContext.getBundle().findEntries(
			getCustomJspDir(), "*.jspf", true);

		while (enumeration.hasMoreElements()) {
			URL url = enumeration.nextElement();

			getCustomJsps().add(url.getPath());
		}
	}
}

