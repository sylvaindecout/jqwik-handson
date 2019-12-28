package fr.xebia.jqwik.exercise9;

import fr.xebia.jqwik.exercise9.ServiceParameters;
import fr.xebia.jqwik.exercise9.ServiceResult;

public interface RemoteService {
    ServiceResult call(ServiceParameters parameters);
}
