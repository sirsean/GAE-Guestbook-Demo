/* 
 * $Id: $
 * $Revision: $
 * $Author: $
 * $Date: $
 * Copyright (c) 2011 Trustwave Holdings, Inc.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information
 * of Trustwave Holdings, Inc.  Use of this software is governed by
 * the terms and conditions of the license statement and limited
 * warranty furnished with the software.
 *
 * IN PARTICULAR, YOU WILL INDEMNIFY AND HOLD TRUSTWAVE HOLDINGS INC.,
 * ITS RELATED COMPANIES AND ITS SUPPLIERS, HARMLESS FROM AND AGAINST
 * ANY CLAIMS OR LIABILITIES ARISING OUT OF OR RESULTING FROM THE USE,
 * MODIFICATION, OR DISTRIBUTION OF PROGRAMS OR FILES CREATED FROM,
 * BASED ON, AND/OR DERIVED FROM THIS SOURCE CODE FILE.
 */
package com.vikinghammer.dao;


/**
 * 
 * 
 * <b>
 * 
 * <pre>
 * Copyright (c) 2011 Trustwave Holdings, Inc.
 * All rights reserved.
 * </pre>
 * 
 * </b>
 * 
 * @author sschulte
 * @version $Revision: $
 */
public class VHQuery {

    private Class clazz;
    private String filter;
    private String ordering;
    private Integer rangeStart;
    private Integer rangeEnd;

    public VHQuery(Class clazz) {
        super();
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }

    public boolean hasFilter() {
        return (filter != null);
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public boolean hasOrdering() {
        return (ordering != null);
    }

    public String getOrdering() {
        return ordering;
    }

    public void setOrdering(String ordering) {
        this.ordering = ordering;
    }

    public boolean hasRange() {
        return ((rangeStart != null) && (rangeEnd != null));
    }

    public void setRange(Integer rangeStart, Integer rangeEnd) {
        this.rangeStart = rangeStart;
        this.rangeEnd = rangeEnd;
    }

    public Integer getRangeStart() {
        return rangeStart;
    }

    public Integer getRangeEnd() {
        return rangeEnd;
    }

}
