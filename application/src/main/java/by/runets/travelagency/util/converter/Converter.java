package by.runets.travelagency.util.converter;

public interface Converter<T, S> {
  T convert(S s);
}