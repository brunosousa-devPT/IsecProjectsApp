package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.AppData;
import pt.isec.pa.apoio_poe.model.fsm.states.*;
public enum AppState {

    BEGIN_STATE, CONFIG_STATE, MANAGE_STUDENTS_STATE, MANAGE_TEACHERS_STATE, MANAGE_POE_STATE, CANDIDATURE_STATE,
    OFFERS_ATTRIBUTION_STATE, ADVISORS_ATTRIBUTION_STATE, QUERY_STATE, MANUAL_ATTRIBUTION_OFFER_STATE,
    MANUAL_ATTRIBUTION_TEACHER_STATE, OFFERS_TIEBREAKER_STATE;


    public IAppState createState(AppContext context, AppData data) {

        return switch (this) {
            case BEGIN_STATE -> new BeginState(context, data);
            case CONFIG_STATE -> new ConfigState(context, data);
            case MANAGE_POE_STATE ->  new ManagePoEState(context,data);
            case MANAGE_STUDENTS_STATE -> new ManageStudentsState(context,data);
            case OFFERS_ATTRIBUTION_STATE -> new OffersAttributionState(context, data);
            case ADVISORS_ATTRIBUTION_STATE -> new AdvisorsAttributionState(context, data);
            case QUERY_STATE -> new QueryState(context, data);
            case CANDIDATURE_STATE -> new CandidatureState(context, data);
            case MANAGE_TEACHERS_STATE->new ManageTeachersState(context, data);
            case MANUAL_ATTRIBUTION_OFFER_STATE -> new ManualAttributionOfferState(context, data);
            case MANUAL_ATTRIBUTION_TEACHER_STATE -> new ManualAttributionTeacherState(context, data);
            case OFFERS_TIEBREAKER_STATE -> new OffersTiebreakerState(context, data);
        };
    }

}
