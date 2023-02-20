package io.goodforgod.jackson.module.datetime.configuration;

import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 11.01.2022
 */
public class JavaTimeModuleConfiguration {

    /**
     * Forces {@link java.time.format.ResolverStyle#STRICT} for all formatters setters
     */
    private boolean forceResolverStrict = false;

    /**
     * Forces {@link IsoChronology#INSTANCE} for all formatters setters
     */
    private boolean forceIsoChronology = false;

    private DateTimeFormatter instantFormat;
    private DateTimeFormatter offsetTimeFormat;
    private DateTimeFormatter offsetDateTimeFormat;
    private DateTimeFormatter zonedDateTimeFormat;
    private DateTimeFormatter localDateTimeFormat;
    private DateTimeFormatter localDateFormat;
    private DateTimeFormatter localTimeFormat;
    private DateTimeFormatter yearFormat;
    private DateTimeFormatter yearMonthFormat;
    private DateTimeFormatter monthDayFormat;

    public JavaTimeModule getModule() {
        return new JavaTimeModule(this);
    }

    public boolean isForceResolverStrict() {
        return forceResolverStrict;
    }

    /**
     * Forces {@link java.time.format.ResolverStyle#STRICT} for all formatters setters
     *
     * @param forceResolverStrict to set
     */
    public JavaTimeModuleConfiguration setForceResolverStrict(boolean forceResolverStrict) {
        this.forceResolverStrict = forceResolverStrict;
        return this;
    }

    public boolean isForceIsoChronology() {
        return forceIsoChronology;
    }

    /**
     * Forces {@link IsoChronology#INSTANCE} for all formatters setters
     *
     * @param forceIsoChronology to set
     */
    public JavaTimeModuleConfiguration setForceIsoChronology(boolean forceIsoChronology) {
        this.forceIsoChronology = forceIsoChronology;
        return this;
    }

    public DateTimeFormatter getInstantFormat() {
        return instantFormat;
    }

    public JavaTimeModuleConfiguration setInstantFormat(DateTimeFormatter instantFormat) {
        this.instantFormat = applyRestrictions(instantFormat);
        return this;
    }

    public JavaTimeModuleConfiguration setInstantFormat(String instantPattern) {
        return setInstantFormat(DateTimeFormatter.ofPattern(instantPattern));
    }

    public DateTimeFormatter getLocalDateFormat() {
        return localDateFormat;
    }

    public JavaTimeModuleConfiguration setLocalDateFormat(DateTimeFormatter localDateFormat) {
        this.localDateFormat = applyRestrictions(localDateFormat);
        return this;
    }

    public JavaTimeModuleConfiguration setLocalDateFormat(String localDatePattern) {
        return setLocalDateFormat(DateTimeFormatter.ofPattern(localDatePattern));
    }

    public DateTimeFormatter getLocalTimeFormat() {
        return localTimeFormat;
    }

    public JavaTimeModuleConfiguration setLocalTimeFormat(DateTimeFormatter localTimeFormat) {
        this.localTimeFormat = applyRestrictions(localTimeFormat);
        return this;
    }

    public JavaTimeModuleConfiguration setLocalTimeFormat(String localTimePattern) {
        return setLocalTimeFormat(DateTimeFormatter.ofPattern(localTimePattern));
    }

    public DateTimeFormatter getLocalDateTimeFormat() {
        return localDateTimeFormat;
    }

    public JavaTimeModuleConfiguration setLocalDateTimeFormat(DateTimeFormatter localDateTimeFormat) {
        this.localDateTimeFormat = applyRestrictions(localDateTimeFormat);
        return this;
    }

    public JavaTimeModuleConfiguration setLocalDateTimeFormat(String localDateTimePattern) {
        return setLocalDateTimeFormat(DateTimeFormatter.ofPattern(localDateTimePattern));
    }

    public DateTimeFormatter getOffsetTimeFormat() {
        return offsetTimeFormat;
    }

    public JavaTimeModuleConfiguration setOffsetTimeFormat(DateTimeFormatter offsetTimeFormat) {
        this.offsetTimeFormat = applyRestrictions(offsetTimeFormat);
        return this;
    }

    public JavaTimeModuleConfiguration setOffsetTimeFormat(String offsetTimePattern) {
        return setOffsetTimeFormat(DateTimeFormatter.ofPattern(offsetTimePattern));
    }

    public DateTimeFormatter getOffsetDateTimeFormat() {
        return offsetDateTimeFormat;
    }

    public JavaTimeModuleConfiguration setOffsetDateTimeFormat(DateTimeFormatter offsetDateTimeFormat) {
        this.offsetDateTimeFormat = applyRestrictions(offsetDateTimeFormat);
        return this;
    }

    public JavaTimeModuleConfiguration setOffsetDateTimeFormat(String offsetDateTimePattern) {
        return setOffsetDateTimeFormat(DateTimeFormatter.ofPattern(offsetDateTimePattern));
    }

    public DateTimeFormatter getZonedDateTimeFormat() {
        return zonedDateTimeFormat;
    }

    public JavaTimeModuleConfiguration setZonedDateTimeFormat(DateTimeFormatter zonedDateTimeFormat) {
        this.zonedDateTimeFormat = applyRestrictions(zonedDateTimeFormat);
        return this;
    }

    public JavaTimeModuleConfiguration setZonedDateTimeFormat(String zonedDateTimePattern) {
        return setZonedDateTimeFormat(DateTimeFormatter.ofPattern(zonedDateTimePattern));
    }

    public DateTimeFormatter getYearFormat() {
        return yearFormat;
    }

    public JavaTimeModuleConfiguration setYearFormat(DateTimeFormatter yearFormat) {
        this.yearFormat = applyRestrictions(yearFormat);
        return this;
    }

    public JavaTimeModuleConfiguration setYearFormat(String yearPattern) {
        return setYearFormat(DateTimeFormatter.ofPattern(yearPattern));
    }

    public DateTimeFormatter getYearMonthFormat() {
        return yearMonthFormat;
    }

    public JavaTimeModuleConfiguration setYearMonthFormat(DateTimeFormatter yearMonthFormat) {
        this.yearMonthFormat = applyRestrictions(yearMonthFormat);
        return this;
    }

    public JavaTimeModuleConfiguration setYearMonthFormat(String yearMonthPattern) {
        return setYearMonthFormat(DateTimeFormatter.ofPattern(yearMonthPattern));
    }

    public DateTimeFormatter getMonthDayFormat() {
        return monthDayFormat;
    }

    public JavaTimeModuleConfiguration setMonthDayFormat(DateTimeFormatter monthDayFormat) {
        this.monthDayFormat = applyRestrictions(monthDayFormat);
        return this;
    }

    public JavaTimeModuleConfiguration setMonthDayFormat(String monthDayPattern) {
        return setMonthDayFormat(DateTimeFormatter.ofPattern(monthDayPattern));
    }

    private DateTimeFormatter applyRestrictions(DateTimeFormatter formatter) {
        DateTimeFormatter processedFormatter = formatter;
        if (forceIsoChronology)
            processedFormatter = processedFormatter.withChronology(IsoChronology.INSTANCE);
        if (forceResolverStrict)
            processedFormatter = processedFormatter.withResolverStyle(ResolverStyle.STRICT);
        return processedFormatter;
    }
}
