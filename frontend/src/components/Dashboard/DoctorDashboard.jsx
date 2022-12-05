const DoctorDashboard = ({ doctor }) => {
	return (
		<div className='dash'>
			<div className='dash-name'>
				<h1>
					Hello Dr. {doctor.type}, id = {doctor.id}
				</h1>
			</div>
			<div className='next-patient'></div>
			<div className='upcoming'></div>
		</div>
	);
};

export default DoctorDashboard;
