package com.example.SpringBookstore;

public enum ReservationStatus {
    PENDING {
        @Override
        public boolean isNextStatePossible(ReservationStatus reservationStatus) {
            return reservationStatus == ReservationStatus.IN_PROGRESS || reservationStatus == ReservationStatus.CANCELLED;
        }
    },
    IN_PROGRESS {
        @Override
        public boolean isNextStatePossible(ReservationStatus reservationStatus) {
            return reservationStatus == ReservationStatus.DELAYED || reservationStatus == ReservationStatus.FINISHED;
        }
    },
    DELAYED {
        @Override
        public boolean isNextStatePossible(ReservationStatus reservationStatus) {
            return reservationStatus == ReservationStatus.FINISHED;
        }
    },
    CANCELLED {
        @Override
        public boolean isNextStatePossible(ReservationStatus reservationStatus) {
            return false;
        }
    },
    FINISHED {
        @Override
        public boolean isNextStatePossible(ReservationStatus reservationStatus) {
            return false;
        }
    };

    public abstract boolean isNextStatePossible(ReservationStatus reservationStatus);
}
