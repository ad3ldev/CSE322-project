const PatientDashboard = ({ patient }) => {
	let isFemale = patient.gender === "F" ? true : false;
	return (
		<div className='dash'>
			<div className='dash-name'>
				{isFemale && (
					<h1>
						Hello Mrs. {patient.type}, id = {patient.id}
					</h1>
				)}
				{!isFemale && (
					<h1>
						Hello Mr. {patient.type}, id = {patient.id}
					</h1>
				)}
			</div>
			<div className='reports'></div>
			<div className='upcoming'></div>
		</div>
	);
};

export default PatientDashboard;
