package com.larvalabs.svgandroid.adt;

import org.xml.sax.Attributes;

import com.larvalabs.svgandroid.util.SAXHelper;
import com.larvalabs.svgandroid.util.SVGParserUtils;

/**
 * @author Larva Labs, LLC
 * @author Nicolas Gramlich
 * @since 16:49:55 - 21.05.2011
 */
public class SVGProperties {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final SVGStyleSet mSVGStyleSet;
	private final Attributes mAttributes;
	private final SVGProperties mParentSVGProperties;

	// ===========================================================
	// Constructors
	// ===========================================================

	public SVGProperties(final Attributes pAttributes, final SVGProperties pParentSVGProperties) {
		this.mAttributes = pAttributes;
		this.mParentSVGProperties = pParentSVGProperties;
		final String styleAttr = SAXHelper.getStringAttribute(pAttributes, "style");
		if (styleAttr != null) {
			this.mSVGStyleSet = new SVGStyleSet(styleAttr);
		} else {
			this.mSVGStyleSet = null;
		}
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public String getStringProperty(final String pPropertyName) {
		String s = null;
		if (this.mSVGStyleSet != null) {
			s = this.mSVGStyleSet.getStyle(pPropertyName);
		}
		if (s == null) {
			s = SAXHelper.getStringAttribute(this.mAttributes, pPropertyName);
		}
		if(s == null) {
			if(this.mParentSVGProperties == null) {
				return null;
			} else {
				return this.mParentSVGProperties.getStringProperty(pPropertyName);
			}
		} else {
			return s;
		}
	}

	public Float getFloatProperty(final String pPropertyName) {
		return SVGParserUtils.parseFloatAttribute(this.getStringProperty(pPropertyName));
	}

	public Float getFloatProperty(final String pPropertyName, final float pDefaultValue) {
		final Float f = this.getFloatProperty(pPropertyName);
		if (f == null) {
			return pDefaultValue;
		} else {
			return f;
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}