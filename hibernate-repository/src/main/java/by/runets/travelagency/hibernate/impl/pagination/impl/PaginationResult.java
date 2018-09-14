package by.runets.travelagency.hibernate.impl.pagination.impl;

import by.runets.travelagency.hibernate.impl.pagination.IPaginationResult;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Query;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class PaginationResult<T> implements IPaginationResult<T> {
	@NonNull
	private Query query;
	@NonNull
	int page;
	@NonNull
	private int limit;
	
	@Override
	public List<T> getResultList () {
		query.setFirstResult((page - 1) * limit);
		query.setMaxResults(limit);
		return query.getResultList();
	}
}
